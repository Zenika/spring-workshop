package com.zenika.petshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableWebSecurity
public class PetshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetshopApplication.class, args);
    }
    
}
