package com.android.testclip.di

import android.content.Context
import androidx.room.Room
import com.android.testclip.BuildConfig
import com.android.testclip.data.local.room.PokemonDatabase
import com.android.testclip.data.local.room.RoomMigrations
import com.android.testclip.data.local.room.daos.PokemonDao
import com.android.testclip.data.remote.retrofit.models.PokemonApi
import com.android.testclip.data.remote.repository.*
import com.android.testclip.domain.repository.IPokemonAbilitiesRepository
import com.android.testclip.domain.repository.IPokemonDetailRepository
import com.android.testclip.domain.repository.IPokemonEvolutionRepository
import com.android.testclip.domain.repository.IPokemonsRepository
import com.android.testclip.domain.use_case.GetEvolutionChain
import com.android.testclip.domain.use_case.PokemonEvolutionUseCases
import com.android.testclip.domain.use_case.SaveAsFavorite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

const val URL_BASE = "https://pokeapi.co/api/v2/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class HttpInterceptorLoggingClient

    @Singleton
    @HttpInterceptorLoggingClient
    @Provides
    fun providesHttpInterceptorLoggingClient(httpInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Singleton
    @Provides
    fun providePokemonApi(@HttpInterceptorLoggingClient httpClient: OkHttpClient): PokemonApi =
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(PokemonApi::class.java)

    @Singleton
    @Provides
    fun providePokemonDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        PokemonDatabase::class.java,
        "Pokemon.db"
    )
        .addMigrations(RoomMigrations.MIGRATION_1_2).build()

    @Singleton
    @Provides
    fun providePokemonDao(db: PokemonDatabase) = db.pokemonDao()

    @Singleton
    @Provides
    fun providePokemonsRepository(api: PokemonApi, dao: PokemonDao): IPokemonsRepository = PokemonsRepository(api,dao)

    @Singleton
    @Provides
    fun providePokemonDetailRepository(api: PokemonApi): IPokemonDetailRepository = PokemonDetailRepository(api)

    @Singleton
    @Provides
    fun providePokemonEvolutionRepository(api: PokemonApi, dao: PokemonDao): IPokemonEvolutionRepository = PokemonEvolutionRepository(api,dao)

    @Singleton
    @Provides
    fun providePokemonAbilityRepository(api: PokemonApi): IPokemonAbilitiesRepository = PokemonAbilitiesRepository(api)

    @Singleton
    @Provides
    fun providePokemonEvolutionUseCases(repository: PokemonEvolutionRepository): PokemonEvolutionUseCases {
        return PokemonEvolutionUseCases(
            getEvolutionChain = GetEvolutionChain(repository),
            saveAsFavorite = SaveAsFavorite(repository)
        )
    }
}