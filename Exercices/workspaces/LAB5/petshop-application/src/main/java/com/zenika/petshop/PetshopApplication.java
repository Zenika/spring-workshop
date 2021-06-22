package com.zenika.petshop;

import com.zenika.petshop.service.OwnerService;
import com.zenika.petshop.service.PetService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Petshop {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PetshopConfig.class);

        new Petshop().run();
    }

    private void run() {
        System.out.println("Listing pets");
        new PetService().listAll();

        System.out.println("Listing owners");
        new OwnerService().listAll();
    }
}
