package com.school.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.school.finance",
                "com.school.baseLayer",
                "com.school.amqp"
        }
)
public class FinanceApp {
    public static void main(String[] args) {
        SpringApplication.run(FinanceApp.class, args);
    }
}
