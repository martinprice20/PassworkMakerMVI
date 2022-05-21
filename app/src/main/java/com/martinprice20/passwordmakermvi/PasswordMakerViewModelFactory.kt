package com.martinprice20.passwordmakermvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martinprice20.passwordmakermvi.utils.ResourceProvider
import java.lang.IllegalArgumentException

class PasswordMakerViewModelFactory(private val resourceProvider: ResourceProvider) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PasswordMakerViewModel::class.java)) {
            return PasswordMakerViewModel(resourceProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}