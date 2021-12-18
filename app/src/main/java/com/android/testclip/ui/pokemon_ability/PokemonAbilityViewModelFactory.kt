package com.android.testclip.ui.pokemon_ability

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.testclip.data.repository.IPokemonAbilitiesRepository

class PokemonAbilityViewModelFactory(private val repository: IPokemonAbilitiesRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonAbilityViewModel::class.java)) {
            return PokemonAbilityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}