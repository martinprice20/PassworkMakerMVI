package com.martinprice20.passwordmakermvi.utils

import android.content.res.Resources
import com.martinprice20.passwordmakermvi.PMmviApp
import com.martinprice20.passwordmakermvi.R

class AndroidResourceAndUtilsProvider(val app: PMmviApp) : ResourceAndUtilsProvider {
    override fun getResources(): Resources {
        return app.applicationContext.resources
    }

    override fun getRandomNum0To99() : Int {
        return (0..99).random()
    }

    override fun getSymbolsList(): List<String> {
        return getResources().getStringArray(R.array.symbols_array).toList()
    }

}