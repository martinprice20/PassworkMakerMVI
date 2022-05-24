package com.martinprice20.passwordmakermvi.views.number

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martinprice20.passwordmakermvi.MainActivity
import com.martinprice20.passwordmakermvi.PasswordMakerViewModel
import com.martinprice20.passwordmakermvi.R
import com.martinprice20.passwordmakermvi.databinding.FragmentNumberBinding
import com.martinprice20.passwordmakermvi.di.component.FragmentComponent
import com.martinprice20.passwordmakermvi.model.NumberAction
import javax.inject.Inject

class NumberFragment : Fragment() {

    private var _binding : FragmentNumberBinding? = null
    private val binding get() = _binding!!
    lateinit var fragmentComponent: FragmentComponent

    @Inject lateinit var viewModel : PasswordMakerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).activityComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNumberBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.numOneIncButton.setOnClickListener { viewModel.reduceNumberState(NumberAction.NumOneInc) }
        binding.numOneRandomButton.setOnClickListener { viewModel.reduceNumberState(NumberAction.NumOneRand) }
        binding.numOneDecButton.setOnClickListener { viewModel.reduceNumberState(NumberAction.NumOneDec) }
        binding.numTwoIncButton.setOnClickListener { viewModel.reduceNumberState(NumberAction.NumTwoInc) }
        binding.numTwoRandomButton.setOnClickListener { viewModel.reduceNumberState(NumberAction.NumTwoRand) }
        binding.numTwoDecButton.setOnClickListener { viewModel.reduceNumberState(NumberAction.NumTwoDec) }
        binding.numThreeIncButton.setOnClickListener { viewModel.reduceNumberState(NumberAction.NumThreeInc) }
        binding.numThreeRandomButton.setOnClickListener { viewModel.reduceNumberState(NumberAction.NumThreeRand) }
        binding.numThreeDecButton.setOnClickListener { viewModel.reduceNumberState(NumberAction.NumThreeDec) }
        binding.numResetButton.setOnClickListener { viewModel.reduceNumberState(NumberAction.ResetNums) }
        viewModel.numState.observe(viewLifecycleOwner) {
            updateView(it)
            enableAddWordsButton(it)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println(viewModel.resources.getResources().getString(R.string.hello_blank_fragment))
        println(viewModel.repository.getWords(10))
    }


    private fun updateView(it: NumberState) {
        binding.numOneText.text = if (it.num1.num == -1) "set me!" else it.num1.num.toString()
        binding.numTwoEditText.text = if (it.num2.num == -1) "set me!" else it.num2.num.toString()
        binding.numThreeEditText.text = if (it.num3.num == -1) "set me!" else it.num3.num.toString()
    }

    private fun enableAddWordsButton(it: NumberState) {
        val isStateValid = it.num1.num != -1 && it.num2.num != -1 && it.num3.num != -1
        binding.goToWordsButton.isEnabled = isStateValid

    }


}