package com.sensysgatso.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = {"com.sensysgatso.assignment.config"})
@EnableScheduling
public class SensysgatsoJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensysgatsoJavaApplication.class, args);
    }

}