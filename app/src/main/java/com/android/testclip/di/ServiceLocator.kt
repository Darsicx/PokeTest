package com.android.testclip.di

object ServiceLocator {

    /*fun providePokemonDataSource(context: Context): PokemonDao {
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

    fun providePokemonEvolutionRepository(context: Context): IPokemonEvolutionRepository {
        return PokemonEvolutionRepository(
            WebService.service,
            providePokemonDataSource(context)
        )
    }

    fun providePokemonEvolutionViewModelFactory(context: Context): PokemonEvolutionViewModelFactory {
        val repository = providePokemonEvolutionRepository(context)
        return PokemonEvolutionViewModelFactory(repository)
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
    }*/
}