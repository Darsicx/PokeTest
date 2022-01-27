package com.android.testclip.domain.use_case

import com.android.testclip.data.remote.repository.PokemonEvolutionRepository
import com.android.testclip.data.remote.retrofit.models.pokemon_evolutive_chain.PokemonFavoriteResponse

class SaveAsFavorite(private val repository: PokemonEvolutionRepository) {

    suspend operator fun invoke(pokemonName: String): PokemonFavoriteResponse {
        return repository.saveAsFavorite(pokemonName)
    }
}