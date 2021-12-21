package com.android.testclip.ui.pokemon_evolution

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.testclip.data.remote.retrofit.models.pokemon_evolutive_chain.EvolvesToDto
import com.android.testclip.data.remote.retrofit.models.pokemon_evolutive_chain.PokemonFavoriteResponse
import com.android.testclip.data.repository.IPokemonEvolutionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonEvolutionViewModel @Inject constructor (private val repository: IPokemonEvolutionRepository) : ViewModel() {

    private val _pokemonEvolutionState: MutableStateFlow<PokemonEvolutionState> =
        MutableStateFlow(PokemonEvolutionState.DEFAULT)
    val pokemonEvolutionState: StateFlow<PokemonEvolutionState> = _pokemonEvolutionState

    private val _pokemonFavoriteState: MutableStateFlow<PokemonFavoriteState> =
        MutableStateFlow(PokemonFavoriteState.DEFAULT)
    val pokemonFavoriteState: StateFlow<PokemonFavoriteState> = _pokemonFavoriteState

    private var pokemonEvolutions: MutableList<String> = mutableListOf()

    fun getPokemonEvolutiveChain(url: String) {
        viewModelScope.launch {
            try {
                _pokemonEvolutionState.value = PokemonEvolutionState.LOADING
                if (pokemonEvolutions.isNotEmpty()) {
                    _pokemonEvolutionState.value =
                        PokemonEvolutionState.SUCCESS(pokemonEvolutions)
                } else {
                    val response = repository.getEvolutionChain(url)
                    getPokemonEvolution(response.chainDto)
                    _pokemonEvolutionState.value =
                        PokemonEvolutionState.SUCCESS(pokemonEvolutions)
                }

            } catch (e: Exception) {
                _pokemonEvolutionState.value =
                    PokemonEvolutionState.ERROR("Pokemon detail infor service error", e)
            }
        }
    }

    fun savePokemonAsFavorite(pokemonName: String) {
        viewModelScope.launch {
            _pokemonFavoriteState.value = PokemonFavoriteState.LOADING
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.saveAsFavorite(pokemonName)
                }

                _pokemonFavoriteState.value =
                    PokemonFavoriteState.SUCCESS(response)
            } catch (e: Exception) {
                _pokemonFavoriteState.value =
                    PokemonFavoriteState.ERROR("Pokemon detail infor service error", e)
            }
        }
    }

    private fun getPokemonEvolution(chain: EvolvesToDto) {
        pokemonEvolutions.add(chain.speciesDto.name)
        if (!chain.evolvesToDto.isNullOrEmpty()) {
            getPokemonEvolution(chain.evolvesToDto[0])
        }
    }

    sealed class PokemonEvolutionState() {
        object DEFAULT : PokemonEvolutionState()
        object LOADING : PokemonEvolutionState()
        data class SUCCESS(val pokemons: List<String>) : PokemonEvolutionState()
        data class ERROR(val cause: String, val exception: Throwable?) : PokemonEvolutionState()
    }

    sealed class PokemonFavoriteState() {
        object DEFAULT : PokemonFavoriteState()
        object LOADING : PokemonFavoriteState()
        data class SUCCESS(val pokemon: PokemonFavoriteResponse) : PokemonFavoriteState()
        data class ERROR(val cause: String, val exception: Throwable?) : PokemonFavoriteState()
    }
}