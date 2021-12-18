package com.android.testclip.data.remote.retrofit.models.pokemon_info


import com.google.gson.annotations.SerializedName

data class PokedexNumberDto(
    @SerializedName("entry_number")
    val entryNumber: Int,
    @SerializedName("pokedex")
    val pokedexDto: com.android.testclip.data.remote.retrofit.models.pokemon_info.PokedexDto
)