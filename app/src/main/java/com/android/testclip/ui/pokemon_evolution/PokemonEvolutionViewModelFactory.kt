package com.android.testclip.ui.pokemon_evolution

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.testclip.domain.repository.IPokemonEvolutionRepository
import com.android.testclip.domain.use_case.PokemonEvolutionUseCases

class PokemonEvolutionViewModelFactory(private val useCases: PokemonEvolutionUseCases) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonEvolutionViewModel::class.java)) {
            return PokemonEvolutionViewModel(useCases) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}