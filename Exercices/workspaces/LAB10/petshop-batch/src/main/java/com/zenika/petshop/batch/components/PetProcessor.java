package com.zenika.petshop.batch.components;

import com.zenika.petshop.model.PetEntity;
import org.springframework.batch.item.*;
import org.springframework.stereotype.Component;

@Component
public class PetProcessor implements ItemProcessor<String[], PetEntity> {
    @Override
    public PetEntity process(String[] data) {
        PetEntity petEntity = new PetEntity();
        petEntity.setId(Integer.parseInt(data[0]));
        petEntity.setName(data[1]);
        petEntity.setRace(data[2]);
        return petEntity;
    }
}
