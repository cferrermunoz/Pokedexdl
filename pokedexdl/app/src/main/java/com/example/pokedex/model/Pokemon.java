package com.example.pokedex.model;

import com.example.pokedex.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pokemon implements Serializable {
    private int Id;
    private String Url;
    private String Name;
    private String Desc;
    private String pokemon_specie;
    private String Photo;
    private boolean Favorite;
    private com.example.pokedex.model.Types type1;
    private com.example.pokedex.model.Types type2;
    private List<Ability> Stats;
    private int Height;
    private int Weight;

    private static ArrayList<Pokemon> mPokemons;

    public Pokemon(int id, String name, String url, String desc, String pokemon_specie, String photo, boolean favorite, Types type1, Types type2, List<Ability> stats, int height, int weight) {
        Id = id;
        Url = url;
        Name = name;
        Desc = desc;
        this.pokemon_specie = pokemon_specie;
        Photo = photo;
        Favorite = favorite;
        this.type1 = type1;
        this.type2 = type2;
        Stats = stats;
        Height = height;
        Weight = weight;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getPokemon_specie() {
        return pokemon_specie;
    }

    public void setPokemon_specie(String pokemon_specie) {
        this.pokemon_specie = pokemon_specie;
    }

    public boolean isFavorite() {
        return Favorite;
    }

    public void setFavorite(boolean favorite) {
        Favorite = favorite;
    }

    public com.example.pokedex.model.Types getType1() {
        return type1;
    }

    public void setType1(com.example.pokedex.model.Types type1) {
        this.type1 = type1;
    }

    public com.example.pokedex.model.Types getType2() {
        return type2;
    }

    public void setType2(com.example.pokedex.model.Types type2) {
        this.type2 = type2;
    }


    public static List<Pokemon> getPokemon(){
        if (mPokemons==null){
            mPokemons = new ArrayList<>();
            ArrayList<Ability> mAbilities = new ArrayList<>();
            mPokemons = new ArrayList<Pokemon>();
            mPokemons.add(new Pokemon(1,"Bulbasur", "https://pokeapi.co/api/v2/pokemon/1", "desc","specie","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/4.svg",false, Types.GRASS, Types.POISON, mAbilities, 1, 1 ));
            mPokemons.add(new Pokemon(2,"Ivysaur", "https://pokeapi.co/api/v2/pokemon/2", "desc","specie","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/4.svg",false, Types.GRASS, Types.POISON, mAbilities, 1, 1 ));
            mPokemons.add(new Pokemon(3,"Venusaur", "https://pokeapi.co/api/v2/pokemon/3", "desc","specie","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/4.svg",false, Types.GRASS, Types.POISON, mAbilities, 1, 1 ));
            mPokemons.add(new Pokemon(4,"Charmander", "https://pokeapi.co/api/v2/pokemon/4", "desc","specie","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/4.svg",false, Types.FIRE, null, mAbilities, 1, 1 ));
            mPokemons.add(new Pokemon(5,"Charmeleon", "https://pokeapi.co/api/v2/pokemon/5", "desc","specie","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/4.svg",false, Types.FIRE, null, mAbilities, 1, 1 ));
            mPokemons.add(new Pokemon(6,"Charizard", "https://pokeapi.co/api/v2/pokemon/6", "desc","specie","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/4.svg",false, Types.FIRE, Types.FLYING, mAbilities, 1, 1 ));
        }
        return mPokemons;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
