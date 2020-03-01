package com.zenika.petshop.persistence;

import com.zenika.petshop.model.Entity;

import java.util.Map;

public interface EntityDao<T extends Entity> {

    Map<Integer, T> readAll();

}
