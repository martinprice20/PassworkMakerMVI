package com.martinprice20.passwordmakermvi.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.martinprice20.passwordmakermvi.PasswordMakerViewModel
import com.martinprice20.passwordmakermvi.R
import com.martinprice20.passwordmakermvi.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

     private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    val viewModel : PasswordMakerViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)


//        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentHomeBinding.inflate(inflater,container, false)
        val root: View = binding.root
        binding.homeStartButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_numberFragment)
        }
        return root
     }
}