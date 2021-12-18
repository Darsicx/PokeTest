package com.android.testclip.data.remote.retrofit.models

import com.android.testclip.data.remote.retrofit.models.pokemon_abilities.PokemonAbilitiesResponse
import com.android.testclip.data.remote.retrofit.models.pokemon_evolutive_chain.PokemonEvolutiveChainResponse
import com.android.testclip.data.remote.retrofit.models.pokemon_info.PokemonInfoResponseDto
import com.android.testclip.data.remote.retrofit.models.pokemon_results.KantoPokemonsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApi {

    @GET("pokemon?")
    fun getKantoPokemons(
        @Query("limit") limit: Int,
    ): Observable<KantoPokemonsResponse>

    @GET("pokemon-species/{name}/")
    suspend fun getPokemonInfo(
        @Path("name") name: String,
    ): PokemonInfoResponseDto

    @GET("pokemon/{name}/")
    suspend fun getPokemonAbillities(
        @Path("name") name: String,
    ): PokemonAbilitiesResponse

    @GET
    suspend fun getEvolutionChain(
        @Url url: String,
    ): PokemonEvolutiveChainResponse


}