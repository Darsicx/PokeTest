package com.android.testclip.ui.pokemon_evolution

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.testclip.data.repository.IPokemonEvolutionRepository

class PokemonEvolutionViewModelFactory(private val repository: IPokemonEvolutionRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonEvolutionViewModel::class.java)) {
            return PokemonEvolutionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}