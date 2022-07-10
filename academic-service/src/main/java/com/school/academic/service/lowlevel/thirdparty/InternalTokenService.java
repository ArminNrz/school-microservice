package com.school.academic.service.lowlevel.thirdparty;

import com.school.baseLayer.enumartion.KeyCloakClientId;
import com.school.clients.security.SecurityFeignClient;
import com.school.clients.security.dto.AuthRequestDTO;
import com.school.clients.security.dto.TokenResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class InternalTokenService {

    private final SecurityFeignClient feignClient;

    @Value("${app.keycloak.username}")
    private String userName;

    @Value("${app.keycloak.password}")
    private String password;

    public String getToken(KeyCloakClientId clientId) {
        log.info("Try to get internal token for client: {}", clientId);
        AuthRequestDTO requestDTO = new AuthRequestDTO();
        requestDTO.setUserName(userName);
        requestDTO.setPassword(password);
        requestDTO.setClientId(clientId);

        TokenResponseDTO responseDTO = feignClient.getToken(requestDTO);
        log.debug("Get internal token from security service for clientId: {}", clientId);
        return "Bearer " + responseDTO.getAccessToken();
    }
}
