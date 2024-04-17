package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.RegisterRequest;
import com.swiftwheelshub.dto.RegistrationResponse;
import com.swiftwheelshub.dto.UserInfo;
import com.swiftwheelshub.dto.UserUpdateRequest;
import com.swiftwheelshub.exception.SwiftWheelsHubException;
import com.swiftwheelshub.exception.SwiftWheelsHubNotFoundException;
import com.swiftwheelshub.exception.SwiftWheelsHubResponseStatusException;
import com.swiftwheelshub.mapper.CustomerMapper;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private static final String ADDRESS = "address";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String USER = "user";
    private static final String OFFLINE_ACCESS = "offline_access";
    private static final String OPENING_BRACE = "{";
    private static final String CLOSE_BRACE = "}";
    private static final String ROLE = "role_";
    private static final String $ = "$";

    @Value("${keycloak.realm}")
    private String realm;

    private final Keycloak keycloak;
    private final BookingService bookingService;
    private final AuthenticationInfoExtractorService authenticationInfoExtractorService;
    private final CustomerMapper customerMapper;

    public List<UserInfo> findAllUsers() {
        return getUsersResource().list()
                .stream()
                .map(customerMapper::mapUserToUserInfo)
                .toList();
    }

    public UserInfo findUserByUsername(String username) {
        UserRepresentation userRepresentation = getUserRepresentation(username);

        return customerMapper.mapUserToUserInfo(userRepresentation);
    }

    public UserInfo getCurrentUser() {
        String username = authenticationInfoExtractorService.getUsername();

        return findUserByUsername(username);
    }

    public Integer countUsers() {
        return getUsersResource().count();
    }

    public RegistrationResponse registerCustomer(RegisterRequest request) {
        validateRequest(request);
        UserRepresentation userRepresentation = createUserRepresentation(request);

        try (Response response = getUsersResource().create(userRepresentation)) {
            final int statusCode = response.getStatus();

            if (HttpStatus.CREATED.value() == statusCode) {
                return getRegistrationResponse(userRepresentation, response, request);
            }

            throw new SwiftWheelsHubResponseStatusException(
                    HttpStatusCode.valueOf(statusCode),
                    "User could not be created: " + response.getStatusInfo().getReasonPhrase()
            );
        }
    }

    public UserInfo updateUser(String id, UserUpdateRequest userUpdateRequest) {
        UserResource userResource = findById(id);

        UserRepresentation userRepresentation = customerMapper.mapToUserRepresentation(userUpdateRequest);
        userRepresentation.singleAttribute(ADDRESS, userUpdateRequest.getAddress());
        userRepresentation.singleAttribute(DATE_OF_BIRTH, userUpdateRequest.getDateOfBirth().toString());

        try {
            userResource.update(userRepresentation);
        } catch (Exception e) {
            handleRestEasyCallException(e);
        }

        return customerMapper.mapUserToUserInfo(userRepresentation);
    }

    public void deleteUserByUsername(String username) {
        UserRepresentation userRepresentation = getUserRepresentation(username);
        UserResource userResource = findById(userRepresentation.getId());

        try {
            userResource.remove();
        } catch (Exception e) {
            handleRestEasyCallException(e);
        }

        bookingService.deleteBookingByCustomerUsername(username);
    }

    public void deleteCurrentUser() {
        String username = authenticationInfoExtractorService.getUsername();
        deleteUserByUsername(username);
    }

    public void signOut() {
        String username = authenticationInfoExtractorService.getUsername();
        UserRepresentation userRepresentation = getUserRepresentation(username);

        try {
            findById(userRepresentation.getId()).logout();
        } catch (Exception e) {
            handleRestEasyCallException(e);
        }
    }

    private UsersResource getUsersResource() {
        return getRealmResource().users();
    }

    private UserResource findById(String id) {
        return getUsersResource().get(id);
    }

    private CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);

        return passwordCredentials;
    }

    private UserRepresentation createUserRepresentation(RegisterRequest request) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(request.getUsername());
        userRepresentation.setFirstName(request.getFirstName());
        userRepresentation.setLastName(request.getLastName());
        userRepresentation.setEmail(request.getEmail());
        userRepresentation.setCredentials(List.of(createPasswordCredentials(request.getPassword())));
        userRepresentation.singleAttribute(ADDRESS, request.getAddress());
        userRepresentation.singleAttribute(DATE_OF_BIRTH, request.getDateOfBirth().toString());
        userRepresentation.setEmailVerified(!request.isNeedsEmailVerification());
        userRepresentation.setEnabled(true);

        return userRepresentation;
    }

    private void verifyEmail(String userId) {
        try {
            findById(userId).sendVerifyEmail();
        } catch (Exception e) {
            handleRestEasyCallException(e);
        }
    }

    private RegistrationResponse getRegistrationResponse(UserRepresentation userRepresentation, Response response,
                                                         RegisterRequest request) {
        String createdId = CreatedResponseUtil.getCreatedId(response);
        UserResource userResource = findById(createdId);
        createRoleUserNonexistent();

        try {
            userResource.resetPassword(createPasswordCredentials(request.getPassword()));
            RoleRepresentation roleRepresentation = getUserRoleRepresentation();
            userResource.roles().realmLevel().add(List.of(roleRepresentation));
        } catch (Exception e) {
            handleRestEasyCallException(e);
        }

        if (request.isNeedsEmailVerification()) {
            verifyEmail(getUserId(userRepresentation.getUsername()));
        }

        return customerMapper.mapToRegistrationResponse(userRepresentation);
    }

    private UserRepresentation getUserRepresentation(String username) {
        List<UserRepresentation> userRepresentations = getUsersResource().searchByUsername(username, true);

        if (userRepresentations.isEmpty()) {
            throw new SwiftWheelsHubNotFoundException("User with username " + username + " doesn't exist");
        }

        return userRepresentations.getFirst();
    }

    private String getUserId(String username) {
        return getUserRepresentation(username).getId();
    }

    private void createRoleUserNonexistent() {
        boolean isRoleNonexistent = getRealmResource().roles()
                .list()
                .stream()
                .map(RoleRepresentation::getName)
                .noneMatch(USER::equals);

        if (isRoleNonexistent) {
            RoleRepresentation roleRepresentation = new RoleRepresentation();
            roleRepresentation.setName(USER);
            roleRepresentation.setDescription($ + OPENING_BRACE + ROLE + USER + CLOSE_BRACE);

            RolesResource rolesResource = getRolesResource();
            rolesResource.create(roleRepresentation);

            getRoleResource().addComposites(List.of(rolesResource.get(OFFLINE_ACCESS).toRepresentation()));
        }
    }

    private RoleRepresentation getUserRoleRepresentation() {
        return getRoleResource().toRepresentation();
    }

    private RoleResource getRoleResource() {
        return getRolesResource().get(USER);
    }

    private RolesResource getRolesResource() {
        return getRealmResource().roles();
    }

    private RealmResource getRealmResource() {
        return keycloak.realm(realm);
    }

    private void validateRequest(RegisterRequest request) {
        if (Optional.ofNullable(request.getPassword()).orElseThrow().length() < 8) {
            throw new SwiftWheelsHubResponseStatusException(HttpStatus.BAD_REQUEST, "Password too short");
        }

        if (Period.between(Optional.ofNullable(request.getDateOfBirth()).orElseThrow(), LocalDate.now()).getYears() < 18) {
            throw new SwiftWheelsHubResponseStatusException(HttpStatus.BAD_REQUEST, "Customer is under 18 years old");
        }
    }

    private void handleRestEasyCallException(Exception e) {
        if (e instanceof NotFoundException) {
            throw new SwiftWheelsHubNotFoundException("User not found");
        }

        if (e instanceof ErrorResponse errorResponse) {
            throw new SwiftWheelsHubResponseStatusException(
                    HttpStatusCode.valueOf(errorResponse.getStatusCode().value()),
                    e.getMessage()
            );
        }

        throw new SwiftWheelsHubException(e.getMessage());
    }

}
