package com.nicolas.pokedexrush.data

import com.google.gson.annotations.SerializedName

// ==========================================
// 1. DATOS PRINCIPALES DEL POKÉMON (/pokemon/{id})
// ==========================================
data class Pokemon(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("sprites") val sprites: Sprites,
    @SerializedName("types") val types: List<PokemonTypeSlot>,
    @SerializedName("stats") val stats: List<PokemonStat>,
    @SerializedName("forms") val forms: List<NamedResource> // Útil para detectar formas regionales o alternativas
)

// ==========================================
// 2. SPRITES Y GALERÍA COMPLETA
// ==========================================
data class Sprites(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("other") val other: OtherSprites?
)

data class OtherSprites(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork?,
    @SerializedName("home") val home: HomeSprites?
)

data class OfficialArtwork(
    @SerializedName("front_default") val frontDefault: String?
)

data class HomeSprites(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?
)

// ==========================================
// 3. TIPOS (MONOTIPO O DOBLE TIPO)
// ==========================================
data class PokemonTypeSlot(
    @SerializedName("slot") val slot: Int, // 1 o 2
    @SerializedName("type") val type: NamedResource
)

// ==========================================
// 4. STATS (HP, Ataque, Defensa, etc.)
// ==========================================
data class PokemonStat(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("stat") val stat: NamedResource
)

// ==========================================
// 5. DESCRIPCIÓN CORTA / GÉNERO (/pokemon-species/{id})
// ==========================================
data class PokemonSpecies(
    @SerializedName("id") val speciesId: Int,
    @SerializedName("genera") val genera: List<Genus>,
    @SerializedName("varieties") val varieties: List<PokemonVariety>? // Clave para las formas regionales
)

data class Genus(
    @SerializedName("genus") val genus: String, // Ej: "Pokémon Ratón"
    @SerializedName("language") val language: NamedResource
)

// ==========================================
// 6. FORMAS REGIONALES / VARIEDADES
// ==========================================
data class PokemonVariety(
    @SerializedName("is_default") val isDefault: Boolean,
    @SerializedName("pokemon") val pokemon: NamedResource // Contiene el nombre y URL de la forma regional (ej: rattata-alola)
)

// Clase comodín reutilizable para recursos con nombre y URL
data class NamedResource(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

// Objeto contenedor que devuelve el endpoint /pokemon
data class PokemonListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<PokemonListItem>
)

// Cada ítem de la lista general
data class PokemonListItem(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String // Trae la URL de donde sacar su ID
)