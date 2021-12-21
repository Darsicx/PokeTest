package com.android.testclip.ui.pokemon_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.testclip.data.remote.retrofit.models.pokemon_info.PokemonInfoResponseDto
import com.android.testclip.data.repository.IPokemonDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val repository: IPokemonDetailRepository) : ViewModel() {

    private val _pokemonDetailState: MutableStateFlow<PokemonDetailState> =
        MutableStateFlow(PokemonDetailState.DEFAULT)
    val pokemonDetailState: StateFlow<PokemonDetailState> = _pokemonDetailState
    var response: PokemonInfoResponseDto? = null

    fun getPokemonsDetail(name: String) {
        viewModelScope.launch {
            try {
                _pokemonDetailState.value = PokemonDetailState.LOADING
                    if (response != null) {
                    _pokemonDetailState.value = PokemonDetailState.SUCCESS(response!!)
                } else {
                    response = repository.getPokemonDetail(name)
                    _pokemonDetailState.value = PokemonDetailState.SUCCESS(response!!)
                }

            } catch (e: Exception) {
                _pokemonDetailState.value =
                    PokemonDetailState.ERROR("Pokemon detail infor service error", e)
            }
        }
    }

    sealed class PokemonDetailState() {
        object DEFAULT : PokemonDetailState()
        object LOADING : PokemonDetailState()
        data class SUCCESS(val pokemons: PokemonInfoResponseDto) : PokemonDetailState()
        data class ERROR(val cause: String, val exception: Throwable?) : PokemonDetailState()
    }
}