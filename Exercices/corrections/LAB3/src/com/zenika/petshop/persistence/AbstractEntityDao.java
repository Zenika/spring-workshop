package com.zenika.petshop.persistence;

import com.zenika.petshop.model.Entity;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

abstract class AbstractEntityDao<T extends Entity> implements EntityDao<T> {

    private final String dataFile;

    protected abstract T createEntity(String[] data);

    public AbstractEntityDao(String dataFile) {
        this.dataFile = dataFile;
    }

    @Override
    public Map<Integer, T> readAll() {
        try (FileInputStream inputStream = new FileInputStream(dataFile)) {
            return IOUtils.readLines(inputStream, StandardCharsets.UTF_8).stream()
                    .map(s -> createEntity(s.split(";")))
                    .collect(Collectors.toMap(Entity::getId, e -> e));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
