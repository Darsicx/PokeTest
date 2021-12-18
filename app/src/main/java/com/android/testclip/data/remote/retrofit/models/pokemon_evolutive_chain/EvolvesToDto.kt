package com.android.testclip.data.remote.retrofit.models.pokemon_evolutive_chain


import com.google.gson.annotations.SerializedName

data class EvolvesToDto(
    @SerializedName("evolution_details")
    val evolutionDetailDtos: List<EvolutionDetailDto>,
    @SerializedName("evolves_to")
    val evolvesToDto: List<EvolvesToDto>,
    @SerializedName("is_baby")
    val isBaby: Boolean,
    @SerializedName("species")
    val speciesDto: SpeciesDto
)