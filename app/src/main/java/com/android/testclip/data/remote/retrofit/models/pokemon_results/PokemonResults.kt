package com.android.testclip.data.remote.retrofit.models.pokemon_results


import com.google.gson.annotations.SerializedName

data class PokemonResults(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)