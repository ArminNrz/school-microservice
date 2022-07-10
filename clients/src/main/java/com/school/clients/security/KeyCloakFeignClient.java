package com.school.clients.security;

import com.school.clients.config.FeignFormConfiguration;
import com.school.clients.security.dto.TokenResponseDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(
        name = "keycloak",
        url = "${keycloak.url}",
        configuration = FeignFormConfiguration.class
)
public interface KeyCloakFeignClient {

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    TokenResponseDTO getToken(Map<String, ?> formParameters);
}
