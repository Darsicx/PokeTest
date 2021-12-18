package com.android.testclip.data.remote.retrofit.models.pokemon_info


import com.google.gson.annotations.SerializedName

data class VarietyDto(
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("pokemon")
    val pokemonDto: com.android.testclip.data.remote.retrofit.models.pokemon_info.PokemonDto
)