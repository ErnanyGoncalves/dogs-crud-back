package com.api.dog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.api.dog")
public class DogApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogApplication.class, args);
    }

}
