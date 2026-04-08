package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        // Bootstraps the Spring context and starts the embedded server.
        SpringApplication.run(EcommerceApplication.class, args);
    }

}
