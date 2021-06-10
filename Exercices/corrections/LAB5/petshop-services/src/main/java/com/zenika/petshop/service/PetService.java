package com.zenika.petshop.service;

import com.zenika.petshop.model.PetEntity;
import com.zenika.petshop.persistence.EntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService extends AbstractEntityService<PetEntity> {

    @Autowired
    private EntityDao<PetEntity> dao;

    @Override
    protected EntityDao<PetEntity> getDao() {
        return dao;
    }
}
