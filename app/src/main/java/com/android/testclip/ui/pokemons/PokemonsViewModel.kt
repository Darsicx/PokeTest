package com.android.testclip.ui.pokemons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.testclip.data.local.room.entities.PokemonEntity
import com.android.testclip.data.remote.retrofit.models.pokemon_results.KantoPokemonsResponse
import com.android.testclip.data.remote.retrofit.models.pokemon_results.mapToPokemonEntity
import com.android.testclip.data.repository.IPokemonsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class PokemonsViewModel(private val repository: IPokemonsRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _pokemonsState: MutableLiveData<PokemonsState> = MutableLiveData()
    val pokemonsState: LiveData<PokemonsState> = _pokemonsState

    fun getPokemonsData() {
        _pokemonsState.value = PokemonsState.LOADING
        repository.getAllPokemonsAtOnce()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {pokemonsList ->
                    if (pokemonsList.isNotEmpty()) {
                        observePokemons()
                    } else {
                        getRemotePokemons()
                    }
                },
                onError = {
                    getRemotePokemons()
                })
            .addTo(compositeDisposable)
    }

    fun getRemotePokemons() {
        repository.getRemotePokemons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { response ->
                    insertPokemonsToDb(response)
                },
                onError = {
                    _pokemonsState.value = PokemonsState.ERROR("Error at pokemons service", it)
                })
            .addTo(compositeDisposable)
    }

    private fun insertPokemonsToDb(response: KantoPokemonsResponse) {
        repository.insertPokemons(response.pokemonResults.map { it.mapToPokemonEntity() })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    observePokemons()
                },
                onError = {
                    _pokemonsState.value = PokemonsState.ERROR("Can´t insert pokemons into db", it)
                })
            .addTo(compositeDisposable)
    }

    fun observePokemons() {
        repository.getAllPokemons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    _pokemonsState.value = PokemonsState.SUCCESS(it)
                },
                onError = {
                    _pokemonsState.value = PokemonsState.ERROR("Can´t observe pokemons table", it)
                })
            .addTo(compositeDisposable)
    }

    sealed class PokemonsState() {
        object LOADING : PokemonsState()
        data class SUCCESS(val pokemons: List<PokemonEntity>) : PokemonsState()
        data class ERROR(val cause: String, val exception: Throwable?) : PokemonsState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}