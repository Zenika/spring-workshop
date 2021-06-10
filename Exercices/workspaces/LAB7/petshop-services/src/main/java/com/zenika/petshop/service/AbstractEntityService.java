package com.zenika.petshop.service;

import com.zenika.petshop.model.Entity;
import com.zenika.petshop.persistence.EntityDao;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractEntityService<T extends Entity> implements EntityService<T> {

    protected abstract EntityDao<T> getDao();

    @Override
    public List<T> getAll() {
        return new ArrayList<>(getDao().readAll().values());
    }

    @Override
    public T findOne(int id) {
        return getDao().readAll().get(id);
    }

    @Override
    public void listAll() {
        getAll().forEach(System.out::println);
    }
}
