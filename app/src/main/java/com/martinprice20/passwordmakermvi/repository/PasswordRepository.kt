package com.martinprice20.passwordmakermvi.repository

import android.util.Log
import com.martinprice20.passwordmakermvi.model.PwWord
import com.martinprice20.passwordmakermvi.repository.networking.WordsApiService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PasswordRepository(private val wordsApiService: WordsApiService) {

    var disposable: CompositeDisposable = CompositeDisposable()


    fun getWords(number: Int, length: Int) : Single<Array<String>> {
       return wordsApiService.getWords(number, length)
    }
}


