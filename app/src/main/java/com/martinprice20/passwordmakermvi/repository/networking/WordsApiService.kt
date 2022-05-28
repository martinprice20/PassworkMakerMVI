package com.martinprice20.passwordmakermvi.repository.networking

import com.martinprice20.passwordmakermvi.model.PwWord
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WordsApiService {

    @GET("word")
    fun getWords(@Query("number") number: Int, @Query("length") length: Int) : Call<Array<String>>
}