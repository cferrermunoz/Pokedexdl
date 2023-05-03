package com.example.pokedex.model;

import java.io.Serializable;

public class Ability implements Serializable {
    private String Name;
    private boolean Active;

    public Ability(String name, boolean active) {
        Name = name;
        Active = active;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }
}
