package com.martinprice20.passwordmakermvi.repository

import android.util.Log
import com.martinprice20.passwordmakermvi.repository.networking.WordsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PasswordRepository(private val wordsApiService: WordsApiService) {

    fun getWords(number: Int) {
        wordsApiService.getWords(10).enqueue(object : Callback<String>  {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("PANIC", "some retrofit callback error")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                println(response.body())
            }
        })
    }

}


