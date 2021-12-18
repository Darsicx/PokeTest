package com.android.testclip.data.remote.retrofit.models.pokemon_info


import com.google.gson.annotations.SerializedName

data class EvolutionChainDto(
    @SerializedName("url")
    val url: String
)