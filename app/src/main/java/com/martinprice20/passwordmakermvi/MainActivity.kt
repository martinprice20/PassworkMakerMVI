package com.martinprice20.passwordmakermvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martinprice20.passwordmakermvi.di.ActivityComponent
import com.martinprice20.passwordmakermvi.di.DaggerActivityComponent
import com.martinprice20.passwordmakermvi.utils.ResourceProvider
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val component : ActivityComponent = DaggerActivityComponent.builder()
        .appComponent((application as PMmviApp).component)
        .build()

    @Inject
    lateinit var mResourceProvider: ResourceProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component.inject(this)
    }
}

