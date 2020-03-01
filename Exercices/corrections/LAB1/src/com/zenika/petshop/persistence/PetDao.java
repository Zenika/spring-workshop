package com.zenika.petshop.persistence;

import com.zenika.petshop.model.PetEntity;

public class PetDao extends AbstractEntityDao<PetEntity> {

    public PetDao(String dataFile) {
        super(dataFile);
    }

    @Override
    protected PetEntity createEntity(String[] data) {
        PetEntity petEntity = new PetEntity();
        petEntity.setId(Integer.parseInt(data[0]));
        petEntity.setName(data[1]);
        petEntity.setRace(data[2]);
        return petEntity;
    }
}
