package com.android.testclip.data.repository

import com.android.testclip.data.local.room.entities.PokemonEntity
import com.android.testclip.data.remote.retrofit.models.pokemon_results.KantoPokemonsResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface IPokemonsRepository {

    fun insertPokemons(pokemons: List<PokemonEntity>): Completable

    fun getAllPokemonsAtOnce(): Single<List<PokemonEntity>>

    fun getAllPokemons(): Flowable<List<PokemonEntity>>

    fun getRemotePokemons(): Observable<KantoPokemonsResponse>

    fun updatePokemonNameById(pokemonName: String, id: Int)

    fun deleteAllPokemons()

}