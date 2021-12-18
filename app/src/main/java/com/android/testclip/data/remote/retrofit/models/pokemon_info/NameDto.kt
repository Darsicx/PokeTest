package com.android.testclip.data.remote.retrofit.models.pokemon_info


import com.google.gson.annotations.SerializedName

data class NameDto(
    @SerializedName("language")
    val languageDto: com.android.testclip.data.remote.retrofit.models.pokemon_info.LanguageXXDto,
    @SerializedName("name")
    val name: String
)