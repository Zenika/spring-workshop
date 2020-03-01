package com.zenika.petshop.persistence;

import com.zenika.petshop.exceptions.WrongDataException;
import com.zenika.petshop.model.OwnerEntity;
import org.apache.commons.lang3.ArrayUtils;

public class OwnerDao extends AbstractEntityDao<OwnerEntity> {

    public OwnerDao(String dataFile) {
        super(dataFile);
    }

    @Override
    protected OwnerEntity createEntity(String[] data) {
        OwnerEntity ownerEntity = new OwnerEntity();
        if (data.length != 2) {
            throw new WrongDataException("Illegal line: " + ArrayUtils.toString(data));
        }
        try {
            int id = Integer.parseInt(data[0]);
            ownerEntity.setId(id);
            ownerEntity.setName(data[1]);
            return ownerEntity;
        } catch (NumberFormatException e) {
            throw new WrongDataException("Number can't be parsed: " + e.getMessage());
        }
    }
}
