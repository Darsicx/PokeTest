package com.android.testclip.data.remote.retrofit.models.pokemon_abilities

import com.google.gson.annotations.SerializedName

data class PokemonAbilitiesResponse(
    @SerializedName("abilities")
    val abilityDtos: List<AbilityDto>

)