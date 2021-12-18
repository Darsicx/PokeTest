package com.android.testclip.data.remote.retrofit.models.pokemon_info


import com.google.gson.annotations.SerializedName

data class FlavorTextEntryDto(
    @SerializedName("flavor_text")
    val flavorText: String,
    @SerializedName("language")
    val languageDto: com.android.testclip.data.remote.retrofit.models.pokemon_info.LanguageDto,
    @SerializedName("version")
    val versionDto: com.android.testclip.data.remote.retrofit.models.pokemon_info.VersionDto
)