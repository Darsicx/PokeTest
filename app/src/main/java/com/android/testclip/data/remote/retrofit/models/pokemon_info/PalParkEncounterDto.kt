package com.android.testclip.data.remote.retrofit.models.pokemon_info


import com.google.gson.annotations.SerializedName

data class PalParkEncounterDto(
    @SerializedName("area")
    val areaDTO: com.android.testclip.data.remote.retrofit.models.pokemon_info.AreaDTO,
    @SerializedName("base_score")
    val baseScore: Int,
    @SerializedName("rate")
    val rate: Int
)