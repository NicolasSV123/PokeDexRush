package com.nicolas.pokedexrush.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    // 1. Obtener el detalle completo de un Pokémon por su ID
    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): Pokemon

    // 2. Obtener los datos de especie (para la descripción corta "Pokémon Ratón" y variedades/formas)
    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") id: Int): PokemonSpecies

    // 3. Obtener la lista general de nombres y URLs (Paginada)
    // Devuelve un objeto PokemonListResponse en lugar de List<Pokemon>
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 1500,
        @Query("offset") offset: Int = 0
    ): PokemonListResponse

    companion object {
        fun getInstance(): PokeApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(PokeApi::class.java)
        }
    }
}

