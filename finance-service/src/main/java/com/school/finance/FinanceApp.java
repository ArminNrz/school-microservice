package com.school.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.school.finance",
                "com.school.baseLayer",
                "com.school.amqp"
        }
)
@EnableFeignClients(
        basePackages = "com.school.clients"
)
public class FinanceApp {
    public static void main(String[] args) {
        SpringApplication.run(FinanceApp.class, args);
    }
}
