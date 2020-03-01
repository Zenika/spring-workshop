package com.zenika.petshop.batch.components;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class PetReader extends FlatFileItemReader<String[]> {

    @Autowired
    private Resource inputResource;

    @PostConstruct
    public void init() {
        setResource(inputResource);
        setLineMapper((line, lineNumber) -> line.split(";"));
    }
}
