package com.zenika.petshop.service;

import com.zenika.petshop.model.OwnerEntity;
import com.zenika.petshop.persistence.EntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService extends AbstractEntityService<OwnerEntity> {

    @Autowired
    private EntityDao<OwnerEntity> dao;

    @Override
    protected EntityDao<OwnerEntity> getDao() {
        return dao;
    }
}
