package com.steve_md.testapp.ui.fragments.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.steve_md.testapp.R
import com.steve_md.testapp.databinding.FragmentLoginAccountBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginAccountFragment : Fragment() {

    private lateinit var binding: FragmentLoginAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginAccountBinding.inflate(inflater, container, false)

        binding.dontHaveAccountSignUpTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginAccountFragment_to_registerAccountFragment)
        }

        return binding.root
    }

}