package com.school.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.security.dto.ResourceAccessDTO;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class KeyCloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    /*
            Map<String, Object> realmAccess = (Map<String, Object>) source.getClaims().get("realm_access");

        if (realmAccess == null || realmAccess.isEmpty()) {
            return new ArrayList<>();
        }

        return ( (List<String>) realmAccess.get("roles"))
                .stream()
                .map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
     */

    @SneakyThrows
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        ObjectMapper objectMapper = new ObjectMapper();
        ResourceAccessDTO resourceAccessDTO = objectMapper.readValue(source.getClaims().get("resource_access").toString(), ResourceAccessDTO.class);
        //ResourceAccessDTO resourceAccessDTO = source.getClaims().get("resource_access");

        if (resourceAccessDTO == null || resourceAccessDTO.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> realmAccess = resourceAccessDTO.getAccount().getRoles();
        realmAccess.addAll(resourceAccessDTO.getAcademicService().getRoles());
        realmAccess.addAll(resourceAccessDTO.getFinanceService().getRoles());

        return realmAccess.stream()
                .map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
