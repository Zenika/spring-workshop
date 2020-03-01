package com.zenika.petshop.persistence;

import com.zenika.petshop.exceptions.WrongDataException;
import com.zenika.petshop.model.OwnerEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class OwnerDao extends AbstractEntityDao<OwnerEntity> {

    @Value("${data.owners}")
    private String dataFilename;

    @Override
    protected String getDataFilename() {
        return dataFilename;
    }

    void setDataFilename(String dataFilename) {
        this.dataFilename = dataFilename;
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
