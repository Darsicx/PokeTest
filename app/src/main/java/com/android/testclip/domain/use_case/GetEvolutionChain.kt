package com.android.testclip.domain.use_case

import com.android.testclip.data.remote.repository.PokemonEvolutionRepository
import com.android.testclip.data.remote.retrofit.models.pokemon_evolutive_chain.PokemonEvolutiveChainResponse

class GetEvolutionChain(private val repository: PokemonEvolutionRepository) {

    suspend operator fun invoke(url: String): PokemonEvolutiveChainResponse {
        return repository.getEvolutionChain(url)
    }
}