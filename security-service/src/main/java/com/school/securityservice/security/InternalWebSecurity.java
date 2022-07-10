package com.school.securityservice.security;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(2)
@EnableWebSecurity
public class InternalWebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/api/security/token");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/security/**")
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}
