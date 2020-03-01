package com.zenika.petshop.persistence;

import com.zenika.petshop.model.PetEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PetDao extends EntityDao<PetEntity> {
}
