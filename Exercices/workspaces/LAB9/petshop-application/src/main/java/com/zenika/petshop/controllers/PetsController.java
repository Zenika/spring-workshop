package com.zenika.petshop.controllers;

import com.zenika.petshop.model.OwnerEntity;
import com.zenika.petshop.model.PetEntity;
import com.zenika.petshop.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PetsController {

    @Autowired
    private EntityService<PetEntity> petService;

    @GetMapping("/pets")
    public Collection<PetEntity> getAll() {
        return petService.getAll();
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<PetEntity> getOne(@PathVariable("id") int id) {
        PetEntity entity = petService.findOne(id);
        return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
    }
}
