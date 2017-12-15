package com.ejemplo.recyclerpokemon;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SERGIO on 14/12/17.
 */

public class PokemonRecyclerAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    private List<Pokemon> pokemonList = new ArrayList<>();

    public PokemonRecyclerAdapter(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public void clear() {
        int size = this.pokemonList.size();
        this.pokemonList.clear();
        notifyItemRangeRemoved(0, size);
    }

    public PokemonRecyclerAdapter() {
        this.pokemonList = new ArrayList<>();
    }

    public void setAddListPokemons(List<Pokemon> pokemonListe) {
        pokemonList.addAll(pokemonListe);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {


        final Pokemon pokemon = pokemonList.get(position);

        holder.getLblNombrePokemon().setText(pokemon.getNombre());


        String urlFoto = pokemon.getUrl();
        Glide.with(holder.itemView.getContext())
                .load(urlFoto)
                .into(holder.getImgFotoPokemon());


    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_pokemon, parent, false);

        return new PokemonViewHolder(v);
    }
}
