package com.nicolas.pokedexrush.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.pokedexrush.R
import com.nicolas.pokedexrush.data.PokemonSpecies
import com.nicolas.pokedexrush.databinding.ItemPokeBinding
import com.squareup.picasso.Picasso

class PokemonAdapter (
    var pokemonList: List<PokemonSpecies>,
    val onItemClick:(position: Int) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPokeBinding.inflate(layoutInflater, parent, false)
        return PokemonViewHolder(binding)
    }
    }
}

class PokemonViewHolder(val binding: ItemPokeBinding) : RecyclerView.ViewHolder(binding.root){
    fun render(pokemon: PokemonSpecies){
        binding.namePokeTextView.text = pokemon.name
        Picasso.get().load(pokemon.sprites.frontDefault).into(binding.pokemonImageView)
    })
}