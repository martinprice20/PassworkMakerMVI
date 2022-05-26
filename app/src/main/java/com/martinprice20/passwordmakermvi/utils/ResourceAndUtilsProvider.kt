package com.martinprice20.passwordmakermvi.utils

import android.content.res.Resources
import com.martinprice20.passwordmakermvi.PMmviApp

interface ResourceAndUtilsProvider {
    fun getResources() : Resources
    fun getRandomNum0To99() : Int
    fun getSymbolsList() : List<String>
}