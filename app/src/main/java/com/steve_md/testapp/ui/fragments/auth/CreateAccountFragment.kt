package com.steve_md.testapp.ui.fragments.auth

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


class CreateAccountFragment : Fragment() {

    // viewBinding
    private lateinit var binding: FragmentCreateAccountBinding

    // View Model
    //private val registerViewModel: AuthViewModel by viewModels()
    private val registerViewModel = activity?.let {
        ViewModelProvider(it)

    }?.get(AuthViewModel::class.java)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateAccountBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                registerViewModel?.registerResult?.collect {
                    when (it) {
                        is Resource.Success -> {
                            toast("Registered Successfully, Please Login")
                            binding.progressBar.visibility = View.GONE
                            Navigation.findNavController(requireView()).navigate(R.id.action_createAccountFragment_to_loginAccountFragment)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.buttonSignUp.isEnabled = true
            //                             binding.root.handleApiError(it, action = {
            //                                 binding.btnSignUp.performClick()
            //                             })
                            toast("An internal error occurred! Server is unreachable")
                        }

                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.buttonSignUp.isEnabled = false
                        }
                        // else -> {}

                    }
                }
            }
        }

        binding.progressBar.visibility = View.GONE

        binding.buttonSignUp.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.buttonSignUp.isEnabled = false

            val userName = binding.enterYourName.text?.trim().toString()
            val userEmail = binding.enterYourEmail.text?.trim().toString()
            val userPassword = binding.enterYourPassword.text?.trim().toString()

            if (binding.enterYourName.text.isNullOrEmpty()
                || binding.enterYourEmail.text.isNullOrEmpty()
                || binding.enterYourPassword.text.isNullOrEmpty() )
            {

                binding.enterYourName.error = "Please enter your Name"
                binding.progressBar.visibility = View.GONE
                binding.buttonSignUp.isEnabled = true
                return@setOnClickListener
            }

            else if (binding.enterYourPassword.text!!.length < 8) {
                binding.enterYourName.error = "Password too short "
                binding.progressBar.visibility = View.GONE
                binding.enterYourPassword.isEnabled = true
                return@setOnClickListener
            }

            else {
                registerViewModel?.registerUser(
                    email = userEmail,
                    name = userName,
                    password = userPassword
                )

                binding.root.hideKeyboard()
            }

        }

        binding.alreadyHaveAccountTextView.setOnClickListener {
           findNavController().navigate(R.id.action_createAccountFragment_to_loginAccountFragment)
        }

    }

}