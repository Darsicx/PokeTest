package com.android.testclip.ui.pokemons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.testclip.domain.repository.IPokemonsRepository

class PokemonsViewModelFactory(private val repository: IPokemonsRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonsViewModel::class.java)) {
            return PokemonsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}