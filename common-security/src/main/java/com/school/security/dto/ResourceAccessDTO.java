package com.school.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ResourceAccessDTO {

    @JsonProperty("academic-service")
    private RolesAccess academicService;
    @JsonProperty("finance-service")
    private RolesAccess financeService;
    private RolesAccess account;

    public boolean isEmpty() {
        return this.account.getRoles().isEmpty() || ( this.academicService.getRoles().isEmpty() && this.financeService.getRoles().isEmpty() );
    }

    @Data
    public static class RolesAccess {
        private ArrayList<String> roles;
    }
}
