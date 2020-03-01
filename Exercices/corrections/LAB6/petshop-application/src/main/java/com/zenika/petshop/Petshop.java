package com.zenika.petshop;

import com.zenika.petshop.model.OwnerEntity;
import com.zenika.petshop.model.PetEntity;
import com.zenika.petshop.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Petshop {

    @Autowired
    private EntityService<OwnerEntity> ownerService;

    @Autowired
    private EntityService<PetEntity> petService;

    public void run() {
        System.out.println("Listing pets");
        petService.listAll();

        System.out.println("Listing owners");
        ownerService.listAll();
    }
}
