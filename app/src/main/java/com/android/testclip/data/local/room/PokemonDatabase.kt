package com.android.testclip.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.testclip.data.local.room.daos.PokemonDao
import com.android.testclip.data.local.room.entities.PokemonEntity

/**
 * The Room database that contains the Pokemon table
 */
@Database(
    entities = [PokemonEntity::class],
    version = 2,
    exportSchema = true,
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    /*companion object {
        @Volatile
        private var INSTANCE: PokemonDatabase? = null

        fun getInstance(context: Context): PokemonDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PokemonDatabase::class.java, "Pokemon.db"
            )
                .addMigrations(RoomMigrations.MIGRATION_1_2)
                .build()
    }*/
}