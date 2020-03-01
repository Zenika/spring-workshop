package com.zenika.petshop.service;

import com.zenika.petshop.model.OwnerEntity;
import com.zenika.petshop.persistence.EntityDao;
import com.zenika.petshop.persistence.OwnerDao;

public class OwnerService extends AbstractEntityService<OwnerEntity> {

    private final OwnerDao dao = new OwnerDao("owners.csv");

    @Override
    protected EntityDao<OwnerEntity> getDao() {
        return dao;
    }
}
