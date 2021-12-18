package com.android.testclip.data.repository

import com.android.testclip.data.local.room.daos.PokemonDao
import com.android.testclip.data.local.room.entities.PokemonEntity
import com.android.testclip.data.remote.retrofit.models.PokemonApi
import com.android.testclip.data.remote.retrofit.models.pokemon_results.KantoPokemonsResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class PokemonsRepository(
    private val service: PokemonApi,
    private val pokemonsDatasource: PokemonDao
): IPokemonsRepository {

    override fun insertPokemons(pokemons: List<PokemonEntity>): Completable {
        return pokemonsDatasource.insertPokemons(pokemons)
    }

    override fun getRemotePokemons(): Observable<KantoPokemonsResponse> {
        return service.getKantoPokemons(151)
    }

    override fun getAllPokemonsAtOnce(): Single<List<PokemonEntity>> {
        return pokemonsDatasource.getAllPokemonsAtOnce()
    }

    override fun getAllPokemons(): Flowable<List<PokemonEntity>> {
        return pokemonsDatasource.getAllPokemons()
    }

    override fun deleteAllPokemons() {
        return pokemonsDatasource.deleteAllPokemons()
    }
}