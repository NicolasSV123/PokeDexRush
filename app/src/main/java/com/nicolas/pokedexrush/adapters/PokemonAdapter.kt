package com.nicolas.pokedexrush.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.pokedexrush.R

class PokemonAdapter(
    private var pokemonList: List<Pokemon> = emptyList(),
    private val onItemClick: (Pokemon) -> Unit = {}
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.namePokeTextView)
        val imageView: ImageView = itemView.findViewById(R.id.pokemonImageView)
        val genusTextView: TextView = itemView.findViewById(R.id.genusPokemonTextView)
        val pokedexNumTextView: TextView = itemView.findViewById(R.id.pokedexNumTextView)

        fun bind(pokemon: Pokemon, onClick: (Pokemon) -> Unit) {
            nameTextView.text = pokemon.name
            pokedexNumTextView.text = "#${pokemon.id}"
            itemView.setOnClickListener { onClick(pokemon) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_poke, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position], onItemClick)
    }

    override fun getItemCount(): Int = pokemonList.size

    fun updateList(newList: List<Pokemon>) {
        pokemonList = newList
        notifyDataSetChanged()
    }
}
