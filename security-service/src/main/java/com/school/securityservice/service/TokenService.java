package com.school.securityservice.service;

import com.school.clients.security.KeyCloakFeignClient;
import com.school.clients.security.dto.TokenResponseDTO;
import com.school.securityservice.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenService {

    private final KeyCloakFeignClient feignClient;

    public TokenResponseDTO getToken(LoginDTO loginDTO) {

        Map<String, String> formParameters = new HashMap<>();
        formParameters.put("grant_type", "password");
        formParameters.put("username", loginDTO.getUserName());
        formParameters.put("password", loginDTO.getPassword());
        formParameters.put("client_id", "academic-service");
        formParameters.put("client_secret", "O7bMXOin8jhap1Xbfyie1WODB25TWcQs");

        log.debug("Try to get token from keycloak server with username: {}", loginDTO.getUserName());
        TokenResponseDTO responseDTO = feignClient.getToken(formParameters);
        log.debug("Get token successfully for user: {}", loginDTO.getUserName());
        return responseDTO;
    }
}
