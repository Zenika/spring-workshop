package com.zenika.petshop.service;

import com.zenika.petshop.model.Entity;

public interface EntityService<T extends Entity> {
    void listAll();
}
