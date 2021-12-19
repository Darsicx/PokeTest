package com.android.testclip.data.repository

import com.android.testclip.data.remote.retrofit.models.pokemon_evolutive_chain.PokemonEvolutiveChainResponse
import com.android.testclip.data.remote.retrofit.models.pokemon_evolutive_chain.PokemonFavoriteResponse

interface IPokemonEvolutionRepository {

    suspend fun getEvolutionChain(url: String): PokemonEvolutiveChainResponse

    suspend fun saveAsFavorite(pokemonName: String): PokemonFavoriteResponse
}