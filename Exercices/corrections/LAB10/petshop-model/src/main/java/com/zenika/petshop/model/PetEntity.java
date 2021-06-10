package com.zenika.petshop.model;

import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "petentity", schema = "public")
public class PetEntity implements Entity {

    @Id
    private int id;

    private String name;
    private String race;

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

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Override
    public String toString() {
        return getId() + " -> " + getName();
    }
}
