package com.steve_md.testapp.ui.fragments.auth

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.steve_md.testapp.R
import com.steve_md.testapp.databinding.FragmentCreateAccountBinding
import com.steve_md.testapp.utils.Resource
import com.steve_md.testapp.utils.hideKeyboard
import com.steve_md.testapp.utils.toast
import com.steve_md.testapp.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.collectLatest


class CreateAccountFragment : Fragment() {


    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!

    // View Model
    private val registerViewModel: AuthViewModel by viewModels()

//    private val registerViewModel = activity?.let {
//        ViewModelProvider(it)
//
//    }?.get(AuthViewModel::class.java)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCreateAccountBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.alreadyHaveAccountTextView.setOnClickListener {
            navigateToLoginAccount()
        }

        binding.buttonSignUp.setOnClickListener {
            if (isValidRegistrationDetails())  {
                val direction = CreateAccountFragmentDirections.actionCreateAccountFragmentToEmailVerificationFragment(
                    binding.enterEmail.text.toString()
                )
                findNavController().navigate(direction)
                registerUser()
            }
            else {
                toast("Unable to register")
            }
        }

        lifecycleScope.launchWhenResumed {
            registerViewModel.registerResult.collectLatest {
                when (it) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        toast(" Registered Successfully, a verification code has been sent to your email.")
                        navigateToVerificationAccount()
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        toast("Couldn't register account!")
                    }

                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    null -> {}

                }
            }
        }


    }

    private fun navigateToLoginAccount() {
        findNavController().navigate(R.id.action_createAccountFragment_to_loginAccountFragment)
    }

    private fun registerUser() {
        registerViewModel.register(binding.enterEmail.text.toString(),binding.enterName.text.toString(), binding.enterPassword.text.toString())
    }

    private fun isValidRegistrationDetails(): Boolean {
       return binding.enterEmail.text.isNullOrEmpty().not().also {
           if (!it) binding.enterYourEmail.error = "Invalid email"
       } && binding.enterName.text.isNullOrEmpty().not().also {
           if (!it) binding.enterYourName.error = "Invalid Name"
       } && binding.enterPassword.text.isNullOrEmpty().not().also {
              if (!it) binding.enterYourPassword.error = "Invalid Password"
       }
    }

    private fun navigateToVerificationAccount() {
        findNavController().navigate(R.id.action_createAccountFragment_to_emailVerificationFragment)
    }
}