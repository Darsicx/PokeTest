package com.android.testclip.data.repository

import com.android.testclip.data.remote.retrofit.models.pokemon_info.PokemonInfoResponseDto
import io.reactivex.Observable

interface IPokemonDetailRepository {

    suspend fun getPokemonDetail(name: String): PokemonInfoResponseDto
}