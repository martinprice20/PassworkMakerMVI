package com.martinprice20.passwordmakermvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.martinprice20.passwordmakermvi.databinding.ActivityMainBinding
import com.martinprice20.passwordmakermvi.di.ActivityComponent
import com.martinprice20.passwordmakermvi.di.DaggerActivityComponent
import com.martinprice20.passwordmakermvi.utils.ResourceProvider
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mResourceProvider: ResourceProvider

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

        val component : ActivityComponent = DaggerActivityComponent.builder()
            .appComponent((application as PMmviApp).component)
            .build()

        component.inject(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || return super.onSupportNavigateUp()
    }
}

