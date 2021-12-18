package com.android.testclip.di

import android.content.Context
import com.android.testclip.data.local.room.PokemonDatabase
import com.android.testclip.data.local.room.daos.PokemonDao
import com.android.testclip.data.remote.retrofit.models.WebService
import com.android.testclip.data.repository.IPokemonDetailRepository
import com.android.testclip.data.repository.IPokemonsRepository
import com.android.testclip.data.repository.PokemonDetailRepository
import com.android.testclip.data.repository.PokemonsRepository
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

    fun providePokemonsDetailRepository(context: Context): IPokemonDetailRepository {
        return PokemonDetailRepository(
            WebService.service
        )
    }

    fun providePokemonDetailViewModelFactory(context: Context): PokemonDetailViewModelFactory {
        val repository = providePokemonsDetailRepository(context)
        return PokemonDetailViewModelFactory(repository)
    }

    fun providePokemonsViewModelFactory(context: Context): PokemonsViewModelFactory {
        val repository = providePokemonsRepository(context)
        return PokemonsViewModelFactory(repository)
    }
}