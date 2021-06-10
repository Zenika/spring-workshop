package com.zenika.petshop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan(basePackageClasses = PetshopConfig.class)
public class PetshopConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]{new ClassPathResource("configuration.properties")};
        placeholderConfigurer.setLocations(resources);
        return placeholderConfigurer;
    }
}
