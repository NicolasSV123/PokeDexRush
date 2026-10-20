package com.nicolas.pokedexrush.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicolas.pokedexrush.R
import com.nicolas.pokedexrush.adapters.PokemonAdapter
import com.nicolas.pokedexrush.data.PokeApi
import com.nicolas.pokedexrush.data.PokemonCardModel
import com.nicolas.pokedexrush.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var adapter: PokemonAdapter

    var pokemonList: List<PokemonCardModel> = emptyList()
    var filteredPokemonList: List<PokemonCardModel> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = PokemonAdapter(emptyList()) { pokemon ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("EXTRA_POKEMON_ID", pokemon.id)
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val api = PokeApi.getInstance()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Pedimos la lista completa (puedes poner limit = 1500 para cubrir todos los existentes)
                val response = api.getPokemonList(limit = 1500, offset = 0)

                val tempList = response.results.map { item ->
                    // Extraemos el ID numérico que viene al final de la URL (ej: ".../pokemon/25/" -> "25")
                    val pokemonId = item.url.trimEnd('/').split("/").last().toIntOrNull() ?: 1

                    PokemonCardModel(
                        id = pokemonId,
                        name = item.name.replaceFirstChar { it.uppercase() },
                        // Construimos la URL de la foto usando su ID exacto
                        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png",
                        type1 = "normal",
                        type2 = null,
                        description = "Pokémon #$pokemonId"
                    )
                }

                pokemonList = tempList
                filteredPokemonList = pokemonList

                CoroutineScope(Dispatchers.Main).launch {
                    showData()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showData(){
        adapter.updateList(filteredPokemonList)
    }
}
