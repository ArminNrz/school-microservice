package com.school.securityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.school.securityservice",
                "com.school.baseLayer",
                "com.school.security"
        }
)
@EnableFeignClients(
        basePackages = "com.school.clients"
)
public class SecurityApp {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApp.class, args);
    }
}
