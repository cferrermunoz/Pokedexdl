package com.example.pokedex.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Equip implements Serializable {

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    private String nom;
    private List<Pokemon> pokemons;
    private static ArrayList<Equip> mEquips;

    public Equip(String nom, List<Pokemon> mPokemons) {
        this.nom = nom;
        pokemons = mPokemons;
    }

    public void addPokemon(Pokemon p){
        if (pokemons.size()<6){
            if (!pokemons.contains(p)){
                pokemons.add(p);
            }
        }
    }

    public static List<Equip> getEquip(){
        if (mEquips==null){
            mEquips = new ArrayList<>();
        }
        return mEquips;
    }

}
