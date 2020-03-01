package com.zenika.petshop;

import com.zenika.petshop.service.OwnerService;
import com.zenika.petshop.service.PetService;

public class Petshop {

    public static void main(String[] args) {
        new Petshop().run();
    }

    private void run() {
        System.out.println("Listing pets");
        new PetService().listAll();

        System.out.println("Listing owners");
        new OwnerService().listAll();
    }
}
