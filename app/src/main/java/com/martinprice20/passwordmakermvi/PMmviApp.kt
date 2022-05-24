package com.martinprice20.passwordmakermvi

import android.app.Application
import com.martinprice20.passwordmakermvi.di.component.AppComponent
import com.martinprice20.passwordmakermvi.di.component.DaggerAppComponent

class PMmviApp: Application() {

    val component: AppComponent = DaggerAppComponent.builder().setApplication(this).build()

    override fun onCreate() {
        super.onCreate()
    }
}