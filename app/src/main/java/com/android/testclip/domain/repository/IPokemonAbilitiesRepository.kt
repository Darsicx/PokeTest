package com.android.testclip.domain.repository

import com.android.testclip.data.remote.retrofit.models.pokemon_abilities.PokemonAbilitiesResponse

interface IPokemonAbilitiesRepository {
    suspend fun getPokemonAbilities(name: String): PokemonAbilitiesResponse
}