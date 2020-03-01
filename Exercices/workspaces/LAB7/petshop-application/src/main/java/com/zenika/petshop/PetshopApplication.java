package com.zenika.petshop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PetshopApplication {

    public static void main(String[] args) {
        new PetshopApplication().run();
    }

    private void run() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PetshopConfig.class);
        applicationContext.getBean(Petshop.class).run();
    }
}
