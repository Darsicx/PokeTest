package com.android.testclip.data.repository

import com.android.testclip.data.remote.retrofit.models.pokemon_info.PokemonInfoResponseDto

interface IPokemonDetailRepository {
    suspend fun getPokemonDetail(name: String): PokemonInfoResponseDto
}