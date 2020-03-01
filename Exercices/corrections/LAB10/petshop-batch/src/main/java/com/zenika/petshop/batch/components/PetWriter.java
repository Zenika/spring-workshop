package com.zenika.petshop.batch.components;

import com.zenika.petshop.model.PetEntity;
import com.zenika.petshop.persistence.PetDao;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class PetWriter implements ItemWriter<PetEntity> {

    @Autowired
    private PetDao petDao;

    @Override
    public void write(List<? extends PetEntity> items) {
        petDao.saveAll(items);
    }
}
