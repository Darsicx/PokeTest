package com.android.testclip.data.repository

import com.android.testclip.data.remote.retrofit.models.PokemonApi
import com.android.testclip.data.remote.retrofit.models.pokemon_abilities.PokemonAbilitiesResponse

class PokemonAbilitiesRepository(private val service: PokemonApi) : IPokemonAbilitiesRepository {

    override suspend fun getPokemonAbilities(name: String): PokemonAbilitiesResponse {
        return service.getPokemonAbillities(name)
    }
}