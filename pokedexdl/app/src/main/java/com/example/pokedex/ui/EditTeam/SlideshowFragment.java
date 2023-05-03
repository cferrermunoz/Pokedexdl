package com.example.pokedex.ui.EditTeam;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.Adapters.EquipsAdadpter;
import com.example.pokedex.R;
import com.example.pokedex.databinding.FragmentSlideshowBinding;
import com.example.pokedex.model.Equip;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.ui.EditTeamFragment;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment implements EquipsAdadpter.EquipSelectedListener {
    private ImageButton btnAdd;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView rcyEquip = binding.rcyEquip;
        rcyEquip.setLayoutManager(new LinearLayoutManager(requireContext()));
        rcyEquip.setHasFixedSize(true);
        EquipsAdadpter adapter = new EquipsAdadpter(Equip.getEquip(),this);
        rcyEquip.setAdapter(adapter);
        btnAdd = binding.btnAdd;
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                Equip e = new Equip("",new ArrayList<Pokemon>());
                args.putSerializable(EditTeamFragment.ARG_PARAM_POKEMON, e);
                NavController navController = NavHostFragment.findNavController(SlideshowFragment.this);
                navController.navigate(R.id.action_nav_slideshow_to_editTeamFragment);
            }
        });
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onEquipSeleccionat(Equip seleccionat) {
        View v = this.getView().findViewById(R.id.nav_host_fragment_content_main2);
        // Preparant els paràmetres del fragment
        Bundle args = new Bundle();
        args.putSerializable(EditTeamFragment.ARG_PARAM_POKEMON, seleccionat);
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_nav_slideshow_to_editTeamFragment, args);
    }
}