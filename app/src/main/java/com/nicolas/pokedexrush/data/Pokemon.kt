package com.nicolas.pokedexrush.data

import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.EncodeDefault
import org.intellij.lang.annotations.Language

data class PokemonDetail (
    @SerializedName("id")val id: Int,
    @SerializedName("name")val name: String,
    @SerializedName("height")val height: Int,
    @SerializedName("weight")val weight: Int,
    )
data class Sprite(
    @SerializedName("front_default") val frontDefault: String?
)
data class PokemonSpecies(
    @SerializedName("genera") val genera: List<Genus>?
)
data class Genus(
    @SerializedName("genus") val genus: String,
    @SerializedName("language") val language: LanguageInfo
)
data class LanguageInfo(
    @SerializedName("name") val name: String
)

data class PokemonCardModel(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val type1: String,
    val type2: String?,
    val description: String
)