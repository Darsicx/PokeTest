package com.android.testclip.data.remote.retrofit.models.pokemon_results

import com.google.gson.annotations.SerializedName

data class KantoPokemonsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val pokemonResults: List<PokemonResults>
)