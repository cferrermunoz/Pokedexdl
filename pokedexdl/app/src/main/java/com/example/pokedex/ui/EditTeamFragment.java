package com.example.pokedex.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokedex.Adapters.PokemonAdapter;
import com.example.pokedex.R;
import com.example.pokedex.databinding.FragmentEditTeamBinding;
import com.example.pokedex.model.Equip;
import com.example.pokedex.model.Pokemon;

public class EditTeamFragment extends Fragment implements PokemonAdapter.PokemonSelectedListener {
    private FragmentEditTeamBinding binding;
    private Equip equip;
    public static final String ARG_PARAM_POKEMON = "equip";

    public EditTeamFragment() {
        // Required empty public constructor
    }

    public static EditTeamFragment newInstance(String param1, String param2) {
        EditTeamFragment fragment = new EditTeamFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Equip equip = (Equip) getArguments().getSerializable(ARG_PARAM_POKEMON);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        binding= FragmentEditTeamBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.rcyPokemons.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rcyPokemons.setHasFixedSize(true);
        PokemonAdapter adapter = new PokemonAdapter(Pokemon.getPokemon(),this);
        binding.rcyPokemons.setAdapter(adapter);
        return root;
    }

    @Override
    public void onPokemonSeleccionat(Pokemon seleccionat) {
        equip.addPokemon(seleccionat);
    }
}