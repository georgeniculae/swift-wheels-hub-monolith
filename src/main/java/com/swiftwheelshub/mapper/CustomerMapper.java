package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.RegistrationResponse;
import com.swiftwheelshub.dto.UserInfo;
import com.swiftwheelshub.dto.UserUpdateRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CustomerMapper {

    String UTC = "UTC";
    String ADDRESS = "address";
    String DATE_OF_BIRTH = "dateOfBirth";

    UserRepresentation mapToUserRepresentation(UserUpdateRequest userUpdateRequest);

    @Mapping(target = "address", expression = "java(getAddress(userRepresentation))")
    @Mapping(target = "dateOfBirth", expression = "java(getDateOfBirth(userRepresentation))")
    UserInfo mapUserToUserInfo(UserRepresentation userRepresentation);

    @Mapping(target = "address", expression = "java(getAddress(userRepresentation))")
    @Mapping(target = "dateOfBirth", expression = "java(getDateOfBirth(userRepresentation))")
    @Mapping(target = "registrationDate", expression = "java(getRegistrationDate())")
    RegistrationResponse mapToRegistrationResponse(UserRepresentation userRepresentation);

    default String getAddress(UserRepresentation userRepresentation) {
        return userRepresentation.getAttributes()
                .getOrDefault(ADDRESS, List.of(StringUtils.EMPTY))
                .getFirst();
    }

    default LocalDate getDateOfBirth(UserRepresentation userRepresentation) {
        String dateOfBirthAsString = userRepresentation.getAttributes()
                .getOrDefault(DATE_OF_BIRTH, List.of(StringUtils.EMPTY))
                .getFirst();

        return ObjectUtils.isEmpty(dateOfBirthAsString) ? null : LocalDate.parse(dateOfBirthAsString);
    }

    default String getRegistrationDate() {
        return ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.of(UTC))
                .truncatedTo(ChronoUnit.SECONDS)
                .format(DateTimeFormatter.ISO_DATE_TIME);
    }

}
