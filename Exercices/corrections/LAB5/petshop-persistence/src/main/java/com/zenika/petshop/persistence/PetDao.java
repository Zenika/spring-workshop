package com.zenika.petshop.persistence;

import com.zenika.petshop.exceptions.WrongDataException;
import com.zenika.petshop.model.PetEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class PetDao extends AbstractEntityDao<PetEntity> {

    @Value("${data.pets}")
    private String dataFilename;

    @Override
    protected String getDataFilename() {
        return dataFilename;
    }

    void setDataFilename(String dataFilename) {
        this.dataFilename = dataFilename;
    }

    @Override
    protected PetEntity createEntity(String[] data) {
        PetEntity petEntity = new PetEntity();
        if (data.length != 3) {
            throw new WrongDataException("Illegal line: " + ArrayUtils.toString(data));
        }
        try {
            petEntity.setId(Integer.parseInt(data[0]));
            petEntity.setName(data[1]);
            petEntity.setRace(data[2]);
            return petEntity;
        } catch (NumberFormatException e) {
            throw new WrongDataException("Number can't be parsed: " + e.getMessage());
        }
    }
}
