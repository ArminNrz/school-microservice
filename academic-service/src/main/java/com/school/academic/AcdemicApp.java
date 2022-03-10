package com.school.academic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.school.academic",
                "com.school.baseLayer"
        }
)
@EnableFeignClients(
        basePackages = "com.school.clients"
)
public class AcdemicApp {
    public static void main(String[] args) {
        SpringApplication.run(AcdemicApp.class, args);
    }
}
