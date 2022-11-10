package com.steve_md.testapp.ui.fragments.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.steve_md.testapp.R
import com.steve_md.testapp.databinding.FragmentLoginAccountBinding
import com.steve_md.testapp.databinding.FragmentRegisterAccountBinding
import com.steve_md.testapp.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint



class RegisterAccountFragment : Fragment() {

    // viewBinding
    private lateinit var binding: FragmentRegisterAccountBinding

    // View Model
    private val registerViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterAccountBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.AlreadyHaveAccountSignInTextView.setOnClickListener {
            findNavController().navigate(R.id.action_registerAccountFragment_to_loginAccountFragment)
        }
    }
}