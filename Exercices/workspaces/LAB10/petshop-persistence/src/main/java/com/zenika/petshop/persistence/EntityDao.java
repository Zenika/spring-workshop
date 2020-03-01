package com.zenika.petshop.persistence;

import com.zenika.petshop.model.Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface EntityDao<T extends Entity> extends CrudRepository<T, Integer> {

    List<T> findAll();
}
