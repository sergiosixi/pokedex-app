package com.ejemplo.recyclerpokemon;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by SERGIO on 14/12/17.
 */

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    private ImageView imgFotoPokemon;
    private TextView lblNombrePokemon;

    public PokemonViewHolder(View view){
        super(view);

        imgFotoPokemon = (ImageView) view.findViewById(R.id.imgPokemon_item);
        lblNombrePokemon = (TextView) view.findViewById(R.id.lblNombrePokemon_item);
    }


    public ImageView getImgFotoPokemon() {
        return imgFotoPokemon;
    }

    public TextView getLblNombrePokemon() {
        return lblNombrePokemon;
    }
}