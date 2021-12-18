package com.android.testclip.data.remote.retrofit.models.pokemon_abilities


import com.google.gson.annotations.SerializedName

data class AbilityDto(
    @SerializedName("ability")
    val abilityDto: AbilityXDto,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("slot")
    val slot: Int
)