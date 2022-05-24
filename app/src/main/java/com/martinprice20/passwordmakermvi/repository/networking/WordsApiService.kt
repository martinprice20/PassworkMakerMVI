package com.martinprice20.passwordmakermvi.repository.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WordsApiService {

    @GET("word")
    fun getWords(@Query("number") number: Int) : Call<String>
}