package com.martinprice20.passwordmakermvi.views.number

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.martinprice20.passwordmakermvi.PasswordMakerViewModel
import com.martinprice20.passwordmakermvi.R
import com.martinprice20.passwordmakermvi.databinding.FragmentNumberBinding
import com.martinprice20.passwordmakermvi.model.Action

class NumberFragment : Fragment() {

    private var _binding : FragmentNumberBinding? = null
    private val binding get() = _binding!!

    val viewModel : PasswordMakerViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNumberBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.numOneIncButton.setOnClickListener { viewModel.reduceNumberState(Action.NumOneInc()) }
        binding.numOneRandomButton.setOnClickListener { viewModel.reduceNumberState(Action.NumOneRand()) }
        binding.numOneDecButton.setOnClickListener { viewModel.reduceNumberState(Action.NumOneDec()) }
        viewModel.numState.observe(viewLifecycleOwner, Observer {
            updateView(it)
        })
    return root
    }


    private fun updateView(it: NumberState?) {
        binding.numOneText.text = it!!.num1.num.toString()
    }


}