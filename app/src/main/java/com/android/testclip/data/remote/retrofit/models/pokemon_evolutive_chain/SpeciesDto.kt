package com.android.testclip.data.remote.retrofit.models.pokemon_evolutive_chain


import com.google.gson.annotations.SerializedName

data class SpeciesDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)