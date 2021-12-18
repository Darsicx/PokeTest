package com.android.testclip.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pokemon")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(
        name = "id_pokemon",
        typeAffinity = ColumnInfo.INTEGER
    ) val pokemonId: Int,
    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT) val name: String,
)
