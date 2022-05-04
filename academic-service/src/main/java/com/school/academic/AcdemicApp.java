package com.school.academic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(
        scanBasePackages = {
                "com.school.academic",
                "com.school.baseLayer",
                "com.school.amqp"
        }
)
@EnableFeignClients(
        basePackages = "com.school.clients"
)
@EnableWebMvc
public class AcdemicApp {
    public static void main(String[] args) {
        SpringApplication.run(AcdemicApp.class, args);
    }
}
