package com.example.pokedex.model;

import java.io.Serializable;

public class BaseStats implements Serializable {
    private String Name;
    private int Value;

    public BaseStats(String name, int value) {
        Name = name;
        Value = value;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }
}
