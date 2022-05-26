package com.martinprice20.passwordmakermvi.views.word

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.martinprice20.passwordmakermvi.MainActivity
import com.martinprice20.passwordmakermvi.PasswordMakerViewModel
import com.martinprice20.passwordmakermvi.R
import com.martinprice20.passwordmakermvi.WordApiState
import com.martinprice20.passwordmakermvi.databinding.FragmentWordBinding
import com.martinprice20.passwordmakermvi.model.PwWord
import com.martinprice20.passwordmakermvi.model.WordAction
import javax.inject.Inject

class WordFragment : Fragment() {

    private var _binding: FragmentWordBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: PasswordMakerViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).activityComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWordBinding.inflate(inflater, container, false)
        //bind some views
        binding.apply {
            wordOneRandomButton.setOnClickListener { viewModel.reduceWordState(WordAction.WordOneRandom) }
            wordTwoRandomButton.setOnClickListener { viewModel.reduceWordState(WordAction.WordTwoRandom) }
            wordThreeRandomButton.setOnClickListener { viewModel.reduceWordState(WordAction.WordThreeRandom) }
            wordsFragmentResetButton.setOnClickListener {
                viewModel.reduceWordState(
                    WordAction.ResetWords
                )
            }
            wordsFragmentContinueButton.setOnClickListener {
                viewModel.reduceWordState(
                    WordAction.SaveWordsAndContinue(getEnteredWords())
                )
                it.findNavController().navigate(R.id.action_wordFragment_to_symbolFragment)
            }
        }
        viewModel.wordState.observe(viewLifecycleOwner) {
            updateView(it)
            enableSaveAndContinueButton()
        }
        viewModel.wordApiState.observe(viewLifecycleOwner) {
            setApiState(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.randomWords
    }

    private fun getEnteredWords(): List<PwWord> {
        return listOf(
            PwWord(binding.wordOneText.toString()),
            PwWord(binding.wordTwoText.toString()),
            PwWord(binding.wordThreeText.toString())
        )
    }

    override fun onDestroy() {
        viewModel.disposable.dispose()
        super.onDestroy()
    }

    private fun setApiState(it: WordApiState?) {
        when(it) {
            WordApiState.DONE -> binding.progressBarContainer.visibility = View.GONE
            WordApiState.LOADING -> binding.progressBarContainer.visibility = View.VISIBLE
            WordApiState.ERROR -> {
                binding.progressBarContainer.visibility = View.GONE
                showErrorDialog()
            }
            else -> {}
        }
    }

    private fun showErrorDialog() {
        TODO("Not yet implemented")
    }

    private fun enableSaveAndContinueButton() {
        binding.wordsFragmentContinueButton.isEnabled =
            !binding.wordOneText.text.isNullOrBlank() &&
                    !binding.wordTwoText.text.isNullOrBlank() &&
                    !binding.wordThreeText.text.isNullOrBlank()
    }

    private fun updateView(it: WordState?) {
        it!!.wordOne.word?.let { binding.wordOneText.setText(it.toString().replaceFirstChar { letter -> letter.uppercase() }) } ?: run {
            binding.wordOneText.text?.clear() }
        it.wordTwo.word?.let { binding.wordTwoText.setText(it.toString().replaceFirstChar { letter -> letter.uppercase() }) } ?: run {
            binding.wordTwoText.text?.clear() }
        it.wordThree.word?.let { binding.wordThreeText.setText(it.toString().replaceFirstChar { letter -> letter.uppercase() }) } ?: run {
            binding.wordThreeText.text?.clear() }
    }



}