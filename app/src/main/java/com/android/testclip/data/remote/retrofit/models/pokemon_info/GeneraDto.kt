package com.android.testclip.data.remote.retrofit.models.pokemon_info


import com.google.gson.annotations.SerializedName

data class GeneraDto(
    @SerializedName("genus")
    val genus: String,
    @SerializedName("language")
    val languageDto: com.android.testclip.data.remote.retrofit.models.pokemon_info.LanguageXDto
)