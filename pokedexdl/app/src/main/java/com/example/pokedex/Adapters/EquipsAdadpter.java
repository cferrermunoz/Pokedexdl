package com.example.pokedex.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pokedex.R;
import com.example.pokedex.model.Equip;
import com.squareup.picasso.Picasso;
import java.util.List;

public class EquipsAdadpter extends RecyclerView.Adapter<EquipsAdadpter.ViewHolderE> {

    private List<Equip> mEquips;
    private int mPosItemsSeleccionat;
    private EquipSelectedListener mListener;
    private int mPosItemSeleccionat = -1;

    public interface EquipSelectedListener {
        public void onEquipSeleccionat(Equip seleccionat);
    }

    public EquipsAdadpter(List<Equip> pEquips, EquipSelectedListener Listener) {
        mEquips=pEquips;
        mListener=Listener;
    }

    @NonNull

    public ViewHolderE onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        ViewHolderE vh = new ViewHolderE(fila);


        // Programació del clic de la fila
        fila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = vh.getAdapterPosition();

                Equip c = mEquips.get(pos);

                if(mListener!=null) mListener.onEquipSeleccionat(c);
                notify();
            }
        });

        return vh;
    }


    public void onBindViewHolder(@NonNull ViewHolderE holder, int position) {
        Equip actual = mEquips.get(position);
        holder.txvName.setText(actual.getNom());
        Picasso.get().load(actual.getPokemons().get(0).getPhoto()).into(holder.imvPokemon1);
        Picasso.get().load(actual.getPokemons().get(1).getPhoto()).into(holder.imvPokemon2);
        Picasso.get().load(actual.getPokemons().get(2).getPhoto()).into(holder.imvPokemon3);
        Picasso.get().load(actual.getPokemons().get(3).getPhoto()).into(holder.imvPokemon4);
        Picasso.get().load(actual.getPokemons().get(4).getPhoto()).into(holder.imvPokemon5);
        Picasso.get().load(actual.getPokemons().get(5).getPhoto()).into(holder.imvPokemon6);
    }


    public int getItemCount() {
        return mEquips.size();
    }

    public class ViewHolderE extends RecyclerView.ViewHolder {
        TextView txvName;
        ImageView imvPokemon1;
        ImageView imvPokemon2;
        ImageView imvPokemon3;
        ImageView imvPokemon4;
        ImageView imvPokemon5;
        ImageView imvPokemon6;
        public ViewHolderE(@NonNull View fila) {
            super(fila);
            txvName = fila.findViewById(R.id.txvName);
            imvPokemon1=fila.findViewById(R.id.imvPok1);
            imvPokemon2=fila.findViewById(R.id.imvPok2);
            imvPokemon3=fila.findViewById(R.id.imvPok3);
            imvPokemon4=fila.findViewById(R.id.imvPok4);
            imvPokemon5=fila.findViewById(R.id.imvPok5);
            imvPokemon6=fila.findViewById(R.id.imvPok6);
        }
    }

}
