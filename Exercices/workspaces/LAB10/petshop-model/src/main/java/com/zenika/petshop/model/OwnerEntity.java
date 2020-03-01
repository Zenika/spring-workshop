package com.zenika.petshop.model;

import javax.persistence.Id;

@javax.persistence.Entity
public class OwnerEntity implements Entity {

    @Id
    private int id;

    private String name;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getId() + " -> " + getName();
    }
}
