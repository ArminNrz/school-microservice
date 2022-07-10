package com.school.securityservice.controller;

import com.school.clients.security.dto.AuthRequestDTO;
import com.school.clients.security.dto.TokenResponseDTO;
import com.school.securityservice.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("api/security/token")
    public ResponseEntity<TokenResponseDTO> getToken(@RequestBody AuthRequestDTO authDTO) {
        log.info("REST request to get token for user: {}", authDTO.getUserName());
        return ResponseEntity.ok().body(tokenService.getToken(authDTO));
    }
}
