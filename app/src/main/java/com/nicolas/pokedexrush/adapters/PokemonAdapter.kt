package com.nicolas.pokedexrush.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.pokedexrush.data.PokemonCardModel
import com.nicolas.pokedexrush.databinding.ItemPokeBinding
import com.squareup.picasso.Picasso

class PokemonAdapter(
    private var pokemonList: List<PokemonCardModel> = emptyList(),
    private val onItemClick: (PokemonCardModel) -> Unit = {}
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(val binding: ItemPokeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: PokemonCardModel, onClick: (PokemonCardModel) -> Unit) {
            val context = itemView.context

            // 1. Textos principales
            binding.namePokeTextView.text = pokemon.name
            binding.genusPokemonTextView.text = pokemon.description
            binding.pokedexNumTextView.text = String.format("#%03d", pokemon.id)

            // 2. Imagen del Pokémon (Sigue viniendo de internet mediante Picasso)
            Picasso.get()
                .load(pokemon.imageUrl)
                .into(binding.pokemonImageView)

            // 3. Cargar Tipo 1 desde la carpeta DRAWABLE local
            val type1ResId = getTipoDrawableResId(context, pokemon.type1)
            if (type1ResId != 0) {
                binding.type1ImageView.setImageResource(type1ResId)
                binding.type1ImageView.visibility = View.VISIBLE
            } else {
                binding.type1ImageView.visibility = View.GONE
            }

            // 4. Cargar Tipo 2 desde la carpeta DRAWABLE local (si existe)
            if (pokemon.type2 != null) {
                val type2ResId = getTipoDrawableResId(context, pokemon.type2)
                if (type2ResId != 0) {
                    binding.type2ImageView.setImageResource(type2ResId)
                    binding.type2ImageView.visibility = View.VISIBLE
                } else {
                    binding.type2ImageView.visibility = View.GONE
                }
            } else {
                // Si es monotipo, ocultamos la segunda imagen
                binding.type2ImageView.visibility = View.GONE
            }

            // 5. Clic en la tarjeta
            itemView.setOnClickListener { onClick(pokemon) }
        }

        // Función que busca la imagen R.drawable.type1_xxx dinámicamente
        private fun getTipoDrawableResId(context: Context, typeName: String): Int {
            val resourceName = "type1_${typeName.lowercase()}"
            return context.resources.getIdentifier(resourceName, "drawable", context.packageName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position], onItemClick)
    }

    override fun getItemCount(): Int = pokemonList.size

    fun updateList(newList: List<PokemonCardModel>) {
        pokemonList = newList
        notifyDataSetChanged()
    }
}