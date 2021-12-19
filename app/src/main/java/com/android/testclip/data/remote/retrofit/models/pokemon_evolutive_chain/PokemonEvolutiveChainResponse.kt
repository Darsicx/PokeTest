package com.android.testclip.data.remote.retrofit.models.pokemon_evolutive_chain

import com.google.gson.annotations.SerializedName

data class PokemonEvolutiveChainResponse(
    @SerializedName("chain")
    val chainDto: EvolvesToDto,
    @SerializedName("id")
    val id: Int
)