package com.example.pokedex;

import android.util.Log;

import com.example.pokedex.model.Ability;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.Types;
import com.example.pokedex.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PokemonApi {
    public static Pokemon getPokemon(int id)
    {
        Pokemon p = null;
        String json = NetworkUtils.getJSon("https://pokeapi.co/api/v2/pokemon/"+id);
        String jsonSpecie = NetworkUtils.getJSon("https://pokeapi.co/api/v2/pokemon-species/"+id);
        try {
            JSONObject pokemonObj = new JSONObject(json);
            String name = pokemonObj.getString("name");
            JSONArray typeArr = pokemonObj.getJSONArray("types");
            String type1 = typeArr.getJSONObject(0).getJSONObject("type").getString("name");
            String type2 = null;
            if (typeArr.length() == 2) {
                type2 = typeArr.getJSONObject(1).getJSONObject("type").getString("name");
            }
            else
                type2 = null;
            JSONObject pokemonSpecieObj = new JSONObject(jsonSpecie);
            JSONObject specieObj = pokemonSpecieObj.getJSONArray("genera").getJSONObject(7);
            String desc = specieObj.getString("genus");
            List<Integer> stats = new ArrayList<>();
            for (int i = 0; i < 6; i++){
                stats.add(pokemonObj.getJSONArray("stats").getJSONObject(i).getInt("base_stat"));
            }
            List<Ability> abilities = new ArrayList<>();
            JSONArray abilitiesArr = pokemonObj.getJSONArray("abilities");
            String nombreAb = "";
            Boolean isOcult = false;
            for (int i = 0; i < abilitiesArr.length(); i++){
                nombreAb = abilitiesArr.getJSONObject(i).getJSONObject("ability").getString("name");
                isOcult = abilitiesArr.getJSONObject(i).getBoolean("is_hidden");
                abilities.add(new Ability(nombreAb, isOcult));
            }
            JSONObject pkmImgObj = pokemonObj.getJSONObject("sprites");
            String img = pkmImgObj.getString("front_default");
            String specie = pokemonSpecieObj.getJSONArray("flavor_text_entries").getJSONObject(0).getString("flavor_text");
            int height = pokemonObj.getInt("height");
            int weight = pokemonObj.getInt("weight");
            p = new Pokemon(id, name, "https://pokeapi.co/api/v2/pokemon/"+id, desc, specie, img, false, Types.valueOf(type1), Types.valueOf(type1), abilities, height, weight);
        } catch (Exception e) {
            Log.e("Error", "Error en generar pokemon",e);
        }
        return p;
    }
}
