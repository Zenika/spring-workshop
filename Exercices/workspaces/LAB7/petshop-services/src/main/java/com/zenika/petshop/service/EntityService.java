package com.zenika.petshop.service;

import com.zenika.petshop.model.Entity;

import java.util.List;

public interface EntityService<T extends Entity> {
    void listAll();

    List<T> getAll();

    T findOne(int id);
}
