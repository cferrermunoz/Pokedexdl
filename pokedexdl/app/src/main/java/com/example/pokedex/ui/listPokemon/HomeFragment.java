package com.example.pokedex.ui.listPokemon;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pokedex.Adapters.PokemonAdapter;
import com.example.pokedex.R;
import com.example.pokedex.databinding.FragmentHomeBinding;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.Types;
import com.example.pokedex.ui.detallFragment.GalleryFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements PokemonAdapter.PokemonSelectedListener, View.OnClickListener {

    private FragmentHomeBinding binding;
    boolean fav = false;
    private File jsonFolder;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.rcyPokemons.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rcyPokemons.setHasFixedSize(true);
        PokemonAdapter adapter = new PokemonAdapter(Pokemon.getPokemon(),this);
        binding.rcyPokemons.setAdapter(adapter);
        ProgressBar pgrdownload = binding.pgrdownload;
        pgrdownload.setVisibility(View.INVISIBLE);
        HomeViewModel viewmodel = new ViewModelProvider(this).get(HomeViewModel.class);
        jsonFolder = new File( this.requireContext().getFilesDir(), "jsons");
        jsonFolder.mkdirs(); // creem la carpeta explícitament
        viewmodel.descarregaPokemons(); // i li passem a la funció de descàrrega
        viewmodel.mutabListPokemons.observe(getViewLifecycleOwner(), new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                pgrdownload.setVisibility(View.GONE);
                for(Pokemon p : pokemons)
                {
                    PokemonAdapter adapter = new PokemonAdapter(pokemons,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter);
                };
            }
        });

        binding.btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnFav = (ImageButton) view;
                if(fav == false){
                    fav=true;
                    btnFav.setImageResource(R.drawable.imagesyf);
                    HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                    viewModel.descarregaPokemons();
                    viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                        List<Pokemon> lp2 = new ArrayList<>();
                        for(Pokemon p : lp){
                            if(p.isFavorite()){
                                lp2.add(p);
                            }
                        }
                        PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                        binding.rcyPokemons.setAdapter(adapter1);;
                    });
                } else {
                    fav = false;
                    btnFav.setImageResource(R.drawable.imagesnf);
                    HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                    viewModel.descarregaPokemons();
                    viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                        PokemonAdapter adapter1 = new PokemonAdapter(lp,HomeFragment.this);
                        binding.rcyPokemons.setAdapter(adapter1);;
                    });
                }

            }
        });
        binding.edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name = binding.edtName.getText().toString();
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getName().contains(name)){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Button btnTypes = binding.btnTypes;
        btnTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });

        return root;
    }

    private void showBottomSheetDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(binding.rcyPokemons.getContext());
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout);

        Button btnAll = bottomSheetDialog.findViewById(R.id.btnAllTypes);
        Button btnFire = bottomSheetDialog.findViewById(R.id.btnFire);
        Button btnWater = bottomSheetDialog.findViewById(R.id.btnWater);
        Button btnElectric = bottomSheetDialog.findViewById(R.id.btnElectric);
        Button btnNormal = bottomSheetDialog.findViewById(R.id.btnNormal);
        Button btnIce = bottomSheetDialog.findViewById(R.id.btnIce);
        Button btnGrass = bottomSheetDialog.findViewById(R.id.btnGrass);
        Button btnPoison = bottomSheetDialog.findViewById(R.id.btnPosion);
        Button btnFighting = bottomSheetDialog.findViewById(R.id.btnFighting);
        Button btnBug = bottomSheetDialog.findViewById(R.id.btnBug);
        Button btnPsychic = bottomSheetDialog.findViewById(R.id.btnPsychic);
        Button btnGhost = bottomSheetDialog.findViewById(R.id.btnGhost);
        Button btnRock = bottomSheetDialog.findViewById(R.id.btnDark);
        Button btnDragon = bottomSheetDialog.findViewById(R.id.btnDragon);
        Button btnFairy = bottomSheetDialog.findViewById(R.id.btnFairy);
        Button btnSteel = bottomSheetDialog.findViewById(R.id.btnSteel);
        Button btnFlying = bottomSheetDialog.findViewById(R.id.btnFlying);


        bottomSheetDialog.show();
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    PokemonAdapter adapter1 = new PokemonAdapter(lp,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);;
                });
                bottomSheetDialog.hide();
            }
        });

        btnBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.BUG || p.getType2()==Types.BUG){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);;
                });
                bottomSheetDialog.hide();
            }
        });

        btnDragon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.DRAGON || p.getType2()==Types.DRAGON){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);;
                });
                bottomSheetDialog.hide();
            }
        });

        btnElectric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.ELECTRIC || p.getType2()==Types.ELECTRIC){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);;
                });
                bottomSheetDialog.hide();
            }
        });

        btnFairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.FAIRY || p.getType2()==Types.FAIRY){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);;
                });
                bottomSheetDialog.hide();
            }
        });

        btnFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.FIRE || p.getType2()==Types.FIRE){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);;
                });
                bottomSheetDialog.hide();
            }
        });

        btnFlying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.FLYING || p.getType2()==Types.FLYING){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);;
                });
                bottomSheetDialog.hide();
            }
        });

        btnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.WATER || p.getType2()==Types.WATER){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);;
                });
                bottomSheetDialog.hide();
            }
        });

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.NORMAL || p.getType2()==Types.NORMAL){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);;
                });
                bottomSheetDialog.hide();
            }
        });

        btnIce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.ICE || p.getType2()==Types.ICE){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);;
                });
                bottomSheetDialog.hide();
            }
        });

        btnGrass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.GRASS || p.getType2()==Types.GRASS){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);;
                });
                bottomSheetDialog.hide();
            }
        });

        btnPoison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.POISON || p.getType2()==Types.POISON){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);;
                });
                bottomSheetDialog.hide();
            }
        });

        btnSteel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.STEEL || p.getType2()==Types.STEEL){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);;
                });
                bottomSheetDialog.hide();
            }
        });

        btnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.ROCK || p.getType2()==Types.ROCK){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);
                });
                bottomSheetDialog.hide();
            }
        });

        btnFighting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.FIGHTING || p.getType2()==Types.FIGHTING){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);
                });
                bottomSheetDialog.hide();
            }
        });

        btnPsychic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.PSYCHIC || p.getType2()==Types.PSYCHIC){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);
                });
                bottomSheetDialog.hide();
            }
        });

        btnGhost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeViewModel viewModel = new ViewModelProvider(HomeFragment.this).get(HomeViewModel.class);
                viewModel.descarregaPokemons();
                viewModel.mutabListPokemons.observe(getViewLifecycleOwner(), lp -> {
                    List<Pokemon> lp2 = new ArrayList<>();
                    for(Pokemon p : lp){
                        if(p.getType1()== Types.GHOST || p.getType2()==Types.GHOST){
                            lp2.add(p);
                        }
                    }
                    PokemonAdapter adapter1 = new PokemonAdapter(lp2,HomeFragment.this);
                    binding.rcyPokemons.setAdapter(adapter1);
                });
                bottomSheetDialog.hide();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPokemonSeleccionat(Pokemon seleccionat) {
        View v = this.getView().findViewById(R.id.nav_host_fragment_content_main2);
        // Preparant els paràmetres del fragment
        Bundle args = new Bundle();
        args.putSerializable(GalleryFragment.ARG_PARAM_POKEMON, seleccionat);
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_nav_home_to_nav_gallery, args);
    }
}