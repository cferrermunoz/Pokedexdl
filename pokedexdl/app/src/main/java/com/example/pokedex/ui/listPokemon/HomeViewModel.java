package com.example.pokedex.ui.listPokemon;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokedex.PokemonApi;
import com.example.pokedex.model.Ability;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.utils.NetworkUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    public MutableLiveData<List<Pokemon>> mutabListPokemons;

    public HomeViewModel() {
        mutabListPokemons = new MutableLiveData<>();
    }

    public void descarregaPokemons()
    {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        Observable.fromCallable(() -> {
            String dir = "./pokedex/";
            //cargo los primeros 20 pokemons de un juego en especifico porque me gusta
            for (int i = 1; i <= 151; i++)
            {
//                File f = new File(dir + "pkm" + i);
                Pokemon p = null;
                p = PokemonApi.getPokemon(i);
                //Si el pkm lo hemos descargado y ya esta en la carpeta  no lo descargamos de nuevo
//                if (f.exists()){
//                    //Añadir pokemon del archivo
//                    try {
//                        Scanner reader = new Scanner(f);
//                        int id = Integer.parseInt(reader.nextLine());
//                        String name = reader.nextLine();
//                        String type1 = reader.nextLine();
//                        String type2 = reader.nextLine();
//                        String desc = reader.nextLine();
//                        List<Integer> stats = new ArrayList<>();
//                        for (int s = 0; i < 6; i++){
//                            stats.add(Integer.parseInt(reader.nextLine()));
//                        }
//                        //Intento de cargar las habilidades
//                        List<Ability> abilities = new ArrayList<>();
//                        Boolean isAbility = false;
//                        if (reader.nextLine().startsWith("startAbilities")){
//                            isAbility = true;
//                        }
//                        while (isAbility){
//                            abilities.add(new Ability(reader.nextLine(),Boolean.parseBoolean(reader.nextLine())));
//                            if (!(reader.nextLine().startsWith("nextAbility"))){
//                                isAbility = false;
//                            }
//                        }
//                        //
//                        String img = reader.nextLine();
//                        String specie = reader.nextLine();
//                        int height = Integer.parseInt(reader.nextLine());
//                        int weight = Integer.parseInt(reader.nextLine());
//                        boolean favorite = Boolean.parseBoolean(reader.nextLine());
//                        p = new Pokemon(id, name, type1, type2, desc, stats, abilities, img, specie, height, weight, favorite);
//                        Log.i("JSONDownload", "Pokemon numero " + id + " añadido desde el txt");
//                    }catch (Exception e){
//                        Log.e("XXX", "He petat a l'hora de llegir l'arxiu", e);
//                    }
//                }
//                else {
//                    p = PokemonApi.getPokemon(i);
//                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));
//                    bw.write(p.getId() + "\n" +
//                            p.getName() + "\n" +
//                            p.getType1() + "\n" +
//                            p.getType2() + "\n" +);
//                    p = new Pokemon(id, name, type1, type2, desc, stats, abilities, img, specie, height, weight, favorite);
//                }
                pokemons.add(p);
            }
            mutabListPokemons.postValue(pokemons);
            return 1;
        }).subscribeOn(Schedulers.io()).subscribe();
    }
}
