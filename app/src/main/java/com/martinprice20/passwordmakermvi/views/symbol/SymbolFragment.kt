package com.martinprice20.passwordmakermvi.views.symbol

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martinprice20.passwordmakermvi.MainActivity
import com.martinprice20.passwordmakermvi.PasswordMakerViewModel
import com.martinprice20.passwordmakermvi.databinding.FragmentSymbolBinding
import com.martinprice20.passwordmakermvi.model.SymbolAction
import javax.inject.Inject

class SymbolFragment : Fragment() {

    private var _binding: FragmentSymbolBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: PasswordMakerViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        super.onAttach(context)
        (activity as MainActivity).activityComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentSymbolBinding.inflate(layoutInflater,container,false)
        binding.apply {
            binding.getRandomSymbolButton.setOnClickListener {
                viewModel.reduceSymbolState(SymbolAction.getNewSymbols)
            }
        }
        viewModel.fiveSymbols.observe(viewLifecycleOwner) {
            updateView(it)
        }
        return binding.root
    }

    private fun updateView(it: MutableList<String>) {
        binding.radioButton.text = it[0]
        binding.radioButton2.text = it[1]
        binding.radioButton3.text = it[2]
        binding.radioButton4.text = it[3]
        binding.radioButton5.text = it[4]

    }


}