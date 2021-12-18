package com.android.testclip.ui.pokemon_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.testclip.data.repository.IPokemonDetailRepository

class PokemonDetailViewModelFactory(private val repository: IPokemonDetailRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonDetailViewModel::class.java)) {
            return PokemonDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}