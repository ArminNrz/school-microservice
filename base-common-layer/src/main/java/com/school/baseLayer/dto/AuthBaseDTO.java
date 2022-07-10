package com.school.baseLayer.dto;

import com.school.baseLayer.enumartion.KeyCloakClientId;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class AuthBaseDTO {
    private String userName;
    private String password;
    private KeyCloakClientId clientId;
}
