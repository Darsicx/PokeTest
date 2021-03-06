package com.android.testclip.ui.pokemon_ability

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.testclip.domain.repository.IPokemonAbilitiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonAbilityViewModel @Inject constructor(private val repository: IPokemonAbilitiesRepository) : ViewModel() {

    private val _pokemonAbilityState: MutableStateFlow<PokemonAbilityState> =
        MutableStateFlow(PokemonAbilityState.DEFAULT)
    val pokemonAbilityState: StateFlow<PokemonAbilityState> =
        _pokemonAbilityState
    var abilities: List<String>? = null

    fun getPokemonAbilities(name: String) {
        viewModelScope.launch {
            try {
                _pokemonAbilityState.value = PokemonAbilityState.LOADING
                    if (abilities != null) {
                    _pokemonAbilityState.value = PokemonAbilityState.SUCCESS(abilities!!)
                } else {
                    val response = repository.getPokemonAbilities(name)
                    abilities = response.abilityDtos.map { it.abilityDto.name }
                    _pokemonAbilityState.value = PokemonAbilityState.SUCCESS(abilities!!)
                }

            } catch (e: Exception) {
                _pokemonAbilityState.value =
                    PokemonAbilityState.ERROR("Pokemon detail infor service error", e)
            }
        }
    }


    sealed class PokemonAbilityState() {
        object DEFAULT : PokemonAbilityState()
        object LOADING : PokemonAbilityState()
        data class SUCCESS(val pokemonAbility: List<String>) : PokemonAbilityState()
        data class ERROR(val cause: String, val exception: Throwable?) : PokemonAbilityState()
    }
}