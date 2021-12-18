package com.android.testclip.data.local.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.testclip.data.local.room.entities.PokemonEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PokemonDao {
    /**
     * Insert pokemons in the database. If the pokemons already exists, ignore it.
     * @param pokemons list of pokemons to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPokemons(pokemons: List<PokemonEntity>): Completable

    /**
     * Get an activity by id.
     * @return the activity from the table with a specific id.
     */
    @Query("SELECT * FROM Pokemon")
    fun getAllPokemons(): Flowable<List<PokemonEntity>>

    /**
     * Delete all pokemons.
     */
    @Query("DELETE FROM Pokemon")
    fun deleteAllPokemons()
}