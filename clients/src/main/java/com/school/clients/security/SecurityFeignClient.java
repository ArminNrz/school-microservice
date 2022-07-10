package com.school.clients.security;

import com.school.clients.config.FeignConfiguration;
import com.school.clients.security.dto.AuthRequestDTO;
import com.school.clients.security.dto.TokenResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "security",
        url = "${clients.security.url}",
        configuration = FeignConfiguration.class
)
public interface SecurityFeignClient {

    @PostMapping("/api/security/token")
    TokenResponseDTO getToken(@RequestBody AuthRequestDTO authRequestDTO);
}
