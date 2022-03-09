package com.school.academic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.school.academic",
                "com.school.baseLayer"
        }
)
public class AcdemicApp {
    public static void main(String[] args) {
        SpringApplication.run(AcdemicApp.class, args);
    }
}
