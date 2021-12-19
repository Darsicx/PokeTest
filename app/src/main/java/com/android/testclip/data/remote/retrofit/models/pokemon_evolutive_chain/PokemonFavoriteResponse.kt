package com.android.testclip.data.remote.retrofit.models.pokemon_evolutive_chain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonFavoriteResponse(
    val pokemonId: Int,
    val pokemonName: String,
    val result: Boolean
): Parcelable
