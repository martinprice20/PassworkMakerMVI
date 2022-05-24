package com.martinprice20.passwordmakermvi.di.module

import com.martinprice20.passwordmakermvi.repository.PasswordRepository
import com.martinprice20.passwordmakermvi.repository.networking.WordsApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import javax.inject.Singleton

const val BASE_URL = "https://random-word-api.herokuapp.com/"

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
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