package com.example.pokedex.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pokedex.R;
import com.example.pokedex.model.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolderP> {

    private List<Pokemon> mPokemon;
    private int mPosItemsSeleccionat;
    private PokemonSelectedListener mListener;
    private int mPosItemSeleccionat = -1;

    public interface PokemonSelectedListener {
        public void onPokemonSeleccionat(Pokemon seleccionat);
    }

    public PokemonAdapter(List<Pokemon> pPokemon, PokemonSelectedListener listener){
        mListener = listener;
        mPokemon = pPokemon;
    }

    @NonNull
    @Override
    public ViewHolderP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        ViewHolderP vh = new ViewHolderP(fila);


        // Programació del clic de la fila
        fila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = vh.getAdapterPosition();

                Pokemon c = mPokemon.get(pos);

                if(mListener!=null) mListener.onPokemonSeleccionat(c);
                notifyItemChanged(pos);
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderP holder, int position) {
        Pokemon actual = mPokemon.get(position);
        holder.grid = holder.itemView.findViewById(R.id.grid);
        holder.txvId.setText("#"+actual.getId()+"");
        holder.txvName.setText(actual.getName());
        Picasso.get().load(actual.getPhoto()).into(holder.imvPhoto);
        holder.chbFav.setSelected(actual.isFavorite());
        holder.txvtype1.setText(" "+actual.getType1().toString()+" ");
        holder.txvtype2.setText(" "+actual.getType2().toString()+" ");
//        if (actual.getType2() == null) {
//            holder.grid.removeView(holder.txvtype2);
//        }
//        else{
//            holder.txvtype2.setText(" "+actual.getType2().toString()+" ");
//        }
    }

    @Override
    public int getItemCount() {
        return mPokemon.size();
    }

    public class ViewHolderP extends RecyclerView.ViewHolder {
        TextView txvName;
        TextView txvId;
        ImageView imvPhoto;
        CheckBox chbFav;
        TextView txvtype1;
        TextView txvtype2;
        GridLayout grid;
        public ViewHolderP(@NonNull View fila) {
            super(fila);
            txvName = fila.findViewById(R.id.txvName);
            txvId = fila.findViewById(R.id.txvId);
            imvPhoto = fila.findViewById(R.id.imvPhoto);
            chbFav = fila.findViewById(R.id.chbFav);
            txvtype1=fila.findViewById(R.id.type1);
            txvtype2=fila.findViewById(R.id.type2);
            grid = fila.findViewById(R.id.grid);
        }
    }
}
