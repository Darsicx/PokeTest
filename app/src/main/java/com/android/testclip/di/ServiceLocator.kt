package com.android.testclip.di

import android.content.Context
import com.android.testclip.data.local.room.PokemonDatabase
import com.android.testclip.data.local.room.daos.PokemonDao
import com.android.testclip.data.remote.retrofit.models.WebService
import com.android.testclip.data.repository.*
import com.android.testclip.ui.pokemon_ability.PokemonAbilityViewModelFactory
import com.android.testclip.ui.pokemon_detail.PokemonDetailViewModelFactory
import com.android.testclip.ui.pokemons.PokemonsViewModelFactory

object ServiceLocator {

    fun providePokemonDataSource(context: Context): PokemonDao {
        val database = PokemonDatabase.getInstance(context.applicationContext)
        return database.pokemonDao()
    }

    fun providePokemonsRepository(context: Context): IPokemonsRepository {
        return PokemonsRepository(
            WebService.service,
            providePokemonDataSource(context)
        )
    }

    fun providePokemonsDetailRepository(): IPokemonDetailRepository {
        return PokemonDetailRepository(
            WebService.service
        )
    }

    fun providePokemonsAbilityRepository(): IPokemonAbilitiesRepository {
        return PokemonAbilitiesRepository(
            WebService.service
        )
    }

    fun providePokemonAbilityViewModelFactory(): PokemonAbilityViewModelFactory {
        val repository = providePokemonsAbilityRepository()
        return PokemonAbilityViewModelFactory(repository)
    }

    fun providePokemonDetailViewModelFactory(): PokemonDetailViewModelFactory {
        val repository = providePokemonsDetailRepository()
        return PokemonDetailViewModelFactory(repository)
    }

    fun providePokemonsViewModelFactory(context: Context): PokemonsViewModelFactory {
        val repository = providePokemonsRepository(context)
        return PokemonsViewModelFactory(repository)
    }
}