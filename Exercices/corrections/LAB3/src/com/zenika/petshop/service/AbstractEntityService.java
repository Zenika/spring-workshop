package com.zenika.petshop.service;

import com.zenika.petshop.model.Entity;
import com.zenika.petshop.persistence.EntityDao;

abstract class AbstractEntityService<T extends Entity> implements EntityService<T> {

    protected abstract EntityDao<T> getDao();

    @Override
    public void listAll() {
        getDao().readAll().values().forEach(System.out::println);
    }
}
