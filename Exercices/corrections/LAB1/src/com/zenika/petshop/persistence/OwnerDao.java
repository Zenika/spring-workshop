package com.zenika.petshop.persistence;

import com.zenika.petshop.model.OwnerEntity;

public class OwnerDao extends AbstractEntityDao<OwnerEntity> {

    public OwnerDao(String dataFile) {
        super(dataFile);
    }

    @Override
    protected OwnerEntity createEntity(String[] data) {
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setId(Integer.parseInt(data[0]));
        ownerEntity.setName(data[1]);
        return ownerEntity;
    }
}
