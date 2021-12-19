package com.android.testclip.data.remote.retrofit.models.pokemon_results


import com.android.testclip.data.local.room.entities.PokemonEntity
import com.google.gson.annotations.SerializedName

data class PokemonResults(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun PokemonResults.mapToPokemonEntity() =
    PokemonEntity(0,this.name,0)