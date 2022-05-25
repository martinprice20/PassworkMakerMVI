package com.martinprice20.passwordmakermvi.di.module

import com.martinprice20.passwordmakermvi.repository.PasswordRepository
import com.martinprice20.passwordmakermvi.repository.networking.WordsApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://random-word-api.herokuapp.com/"

@Module
class RepositoryModule {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun providesWordsApiService(retrofit: Retrofit) : WordsApiService {
        return retrofit.create(WordsApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesPasswordRepository(wordsApiService: WordsApiService) : PasswordRepository {
        return PasswordRepository(wordsApiService)
    }
}