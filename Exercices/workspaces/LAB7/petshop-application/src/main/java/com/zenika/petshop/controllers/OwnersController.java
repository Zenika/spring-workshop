package com.zenika.petshop.controllers;

import com.zenika.petshop.model.OwnerEntity;
import com.zenika.petshop.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class OwnersController {

    @Autowired
    private EntityService<OwnerEntity> ownerService;

    @GetMapping("/owners")
    public Collection<OwnerEntity> getAll() {
        return ownerService.getAll();
    }

    @GetMapping("/owners/{id}")
    public ResponseEntity<OwnerEntity> getOne(@PathVariable("id") int id) {
        OwnerEntity entity = ownerService.findOne(id);
        return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
    }
}
