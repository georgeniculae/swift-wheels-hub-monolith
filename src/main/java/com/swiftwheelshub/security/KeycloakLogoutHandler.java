package com.swiftwheelshub.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
@Slf4j
public class KeycloakLogoutHandler implements LogoutHandler {

    private final RestClient restClient;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        OidcUser principal = (OidcUser) authentication.getPrincipal();

        String endSessionEndpoint = principal.getIssuer() + "/protocol/openid-connect/logout";
        UriComponentsBuilder uriComponentsBuilder = getUriComponentsBuilder(endSessionEndpoint, principal);

        ResponseEntity<String> logoutResponse = getLogoutResponse(uriComponentsBuilder);

        if (logoutResponse.getStatusCode().is2xxSuccessful()) {
            log.info("Successfully logged out from Keycloak");
        } else {
            log.error("Could not propagate logout to Keycloak");
        }
    }

    private UriComponentsBuilder getUriComponentsBuilder(String endSessionEndpoint, OidcUser principal) {
        return UriComponentsBuilder
                .fromUriString(endSessionEndpoint)
                .queryParam("id_token_hint", principal.getIdToken().getTokenValue());
    }

    private ResponseEntity<String> getLogoutResponse(UriComponentsBuilder builder) {
        return restClient.get()
                .uri(builder.toUriString())
                .retrieve()
                .toEntity(String.class);
    }

}
