package com.school.clients.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenResponseDTO {

    @JsonProperty(value = "access_token")
    private String accessToken;
}
