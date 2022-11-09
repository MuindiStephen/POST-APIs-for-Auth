package com.steve_md.testapp.ui.fragments.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.steve_md.testapp.R
import com.steve_md.testapp.databinding.FragmentLoginAccountBinding
import com.steve_md.testapp.databinding.FragmentRegisterAccountBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterAccountFragment : Fragment() {

    private lateinit var binding: FragmentRegisterAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterAccountBinding.inflate(inflater, container, false)

        binding.AlreadyHaveAccountSignInTextView.setOnClickListener {
            findNavController().navigate(R.id.action_registerAccountFragment_to_loginAccountFragment)
        }

        return binding.root
    }
}