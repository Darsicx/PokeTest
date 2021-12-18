package com.android.testclip.data.remote.retrofit.models.pokemon_abilities


import com.google.gson.annotations.SerializedName

data class AbilityXDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)