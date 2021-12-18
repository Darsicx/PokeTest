package com.android.testclip.data.repository

import com.android.testclip.data.remote.retrofit.models.PokemonApi
import com.android.testclip.data.remote.retrofit.models.pokemon_info.PokemonInfoResponseDto

class PokemonDetailRepository(private val service: PokemonApi) : IPokemonDetailRepository {

    override suspend fun getPokemonDetail(name: String): PokemonInfoResponseDto {
        return service.getPokemonInfo(name)
    }
}