package com.zenika.petshop.service;

import com.zenika.petshop.model.PetEntity;
import com.zenika.petshop.persistence.EntityDao;
import com.zenika.petshop.persistence.PetDao;

public class PetService extends AbstractEntityService<PetEntity> {

    private PetDao dao = new PetDao("pets.csv");

    @Override
    protected EntityDao<PetEntity> getDao() {
        return dao;
    }
}
