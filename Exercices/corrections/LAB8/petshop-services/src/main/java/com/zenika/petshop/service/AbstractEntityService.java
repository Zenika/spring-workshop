package com.zenika.petshop.service;

import com.zenika.petshop.model.Entity;
import com.zenika.petshop.persistence.EntityDao;

import java.util.List;

abstract class AbstractEntityService<T extends Entity> implements EntityService<T> {

    protected abstract EntityDao<T> getDao();

    @Override
    public List<T> getAll() {
        return getDao().findAll();
    }

    @Override
    public T findOne(int id) {
        return getDao().findById(id).orElse(null);
    }

    @Override
    public void listAll() {
        getAll().forEach(System.out::println);
    }
}
