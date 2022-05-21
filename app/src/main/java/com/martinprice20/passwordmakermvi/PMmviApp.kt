package com.martinprice20.passwordmakermvi

import android.app.Application
import com.martinprice20.passwordmakermvi.di.AppComponent
import com.martinprice20.passwordmakermvi.di.DaggerAppComponent

class PMmviApp: Application() {

    val component: AppComponent = DaggerAppComponent.builder().setApplication(this).build()

    override fun onCreate() {
        super.onCreate()
    }
}