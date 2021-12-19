package com.android.testclip.data.repository

import com.android.testclip.data.local.room.daos.PokemonDao
import com.android.testclip.data.remote.retrofit.models.PokemonApi
import com.android.testclip.data.remote.retrofit.models.pokemon_evolutive_chain.PokemonEvolutiveChainResponse
import com.android.testclip.data.remote.retrofit.models.pokemon_evolutive_chain.PokemonFavoriteResponse
import kotlinx.coroutines.delay

class PokemonEvolutionRepository(
    private val service: PokemonApi,
    private val pokemonsDatasource: PokemonDao
) : IPokemonEvolutionRepository {

    override suspend fun getEvolutionChain(url: String): PokemonEvolutiveChainResponse {
        return service.getEvolutionChain(url)
    }

    override suspend fun saveAsFavorite(pokemonName: String): PokemonFavoriteResponse {
        delay(5000)
        val pokemon = pokemonsDatasource.getPokemonByName(pokemonName)
        val random = (1..2).random()
        //val text = if (random == 1) "Favorito - " else "Error - "
        //pokemonsDatasource.updatePokemonNameById("$text $pokemonName",pokemon.pokemonId)
        return if(pokemon != null) {
            pokemonsDatasource.updatePokemonStatusById(random,pokemon.pokemonId)
             PokemonFavoriteResponse(pokemon.pokemonId,pokemonName, random == 1)
        } else {
            PokemonFavoriteResponse(0,"", false)
        }

    }
}