package com.school.securityservice.service;

import com.school.baseLayer.enumartion.KeyCloakClientId;
import com.school.clients.security.KeyCloakFeignClient;
import com.school.clients.security.dto.AuthRequestDTO;
import com.school.clients.security.dto.TokenResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.school.baseLayer.enumartion.KeyCloakClientId.ACADEMIC;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenService {

    private final KeyCloakFeignClient feignClient;

    @Value("${app.academic-client-secret}")
    private String academicClientSecret;

    @Value("${app.finance-client-secret}")
    private String financeClientSecret;

    public TokenResponseDTO getToken(AuthRequestDTO authDTO) {

        Map<String, String> formParameters = buildFormParameters(authDTO);

        log.debug("Try to get token from keycloak server with username: {}", authDTO.getUserName());
        TokenResponseDTO responseDTO = feignClient.getToken(formParameters);
        log.debug("Get token successfully for user: {}", authDTO.getUserName());
        return responseDTO;
    }

    private Map<String, String> buildFormParameters(AuthRequestDTO authDTO) {

        KeyCloakClientId clientId = authDTO.getClientId();

        Map<String, String> formParameters = new HashMap<>();
        formParameters.put("grant_type", "password");
        formParameters.put("username", authDTO.getUserName());
        formParameters.put("password", authDTO.getPassword());
        formParameters.put("client_id", clientId.getLabel());
        formParameters.put("client_secret", clientId == ACADEMIC ? academicClientSecret : financeClientSecret);
        return formParameters;
    }
}
