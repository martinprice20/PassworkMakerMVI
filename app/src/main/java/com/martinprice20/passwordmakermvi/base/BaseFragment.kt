package com.martinprice20.passwordmakermvi.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martinprice20.passwordmakermvi.PMmviApp
import com.martinprice20.passwordmakermvi.R
import com.martinprice20.passwordmakermvi.di.DaggerFragmentComponent
import com.martinprice20.passwordmakermvi.di.FragmentComponent

open class BaseFragment : Fragment() {

    val component: FragmentComponent = DaggerFragmentComponent.builder()
        .appComponent((requireActivity().application as PMmviApp).component)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}