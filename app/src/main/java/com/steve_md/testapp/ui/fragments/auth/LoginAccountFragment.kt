package com.steve_md.testapp.ui.fragments.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.steve_md.testapp.R
import com.steve_md.testapp.databinding.FragmentLoginAccountBinding
import com.steve_md.testapp.utils.Resource
import com.steve_md.testapp.utils.toast
import com.steve_md.testapp.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber


class LoginAccountFragment : Fragment() {

    // According to the docs this is a nice way get binding
    private var _binding: FragmentLoginAccountBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()
//    private val viewModel = activity?.let {
//        ViewModelProvider(it)
//    }?.get(AuthViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dontHaveAccountSignUpTextView.setOnClickListener {
            navigateToCreateAccount()
        }

        binding.buttonLogin.setOnClickListener {
            if (isValidCredentials()) loginUser()
            else toast("Enter valid credentials")
        }

        lifecycleScope.launchWhenResumed {
            viewModel.loginResult.collectLatest {
                when (it) {
                    Resource.Loading -> {
                        toast("Loading")
                    }
                    is Resource.Error -> {
                        toast("Couldn't log in")
                    }
                    is Resource.Success -> {
                        val userId = it.value.data

                        // check whether user data is null or available in the backend db api
                        it.value.data.let {

                            // if available then login the user successfully.
                            if (it !=null) {
                                toast("Successfully Logged In")
                                navigateToHome()
                            } else {
                                toast("Account Doesn't exist")
                            }
                        }

                    }
                    null -> {}
                }
            }
        }

    }

    private fun isValidCredentials(): Boolean {
        return binding.inputEmail.text.isNullOrEmpty().not().also {
            if (!it) binding.txtLayEmailAdd.error = "Invalid Email"
        } && binding.inputPassword.text.isNullOrEmpty().not().also {
            if (!it) binding.txtLayPassSignup.error = "Invalid Password"
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.action_loginAccountFragment_to_homeFragment)
    }

    private fun navigateToCreateAccount() {
        findNavController().navigate(R.id.action_loginAccountFragment_to_createAccountFragment)
    }

    private fun loginUser() {
        Log.i("VAVAVA","VIEWMODEL IS nUll -> ${viewModel == null}")
        viewModel.login(binding.inputEmail.text.toString(), binding.inputPassword.text.toString())
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        // Inflate the layout for this fragment
//        binding = FragmentLoginAccountBinding.inflate(layoutInflater, container, false)
//
//
//        binding.dontHaveAccountSignUpTextView.setOnClickListener {
//            findNavController().navigate(R.id.action_loginAccountFragment_to_createAccountFragment)
//        }
//
//
//        val loginRequest = LoginRequest(
//            binding.inputEmail.editableText.toString(),
//            binding.inputPassword.editableText.toString()
//        )
//
//        binding.buttonLogin.setOnClickListener {
//
//            if (binding.inputEmail.text.isNullOrEmpty() && binding.inputPassword.text.isNullOrEmpty()) {
//                toast("Enter credentials")
//            }
//            else {
//            lifecycleScope.launch {
//                loginViewModel?.postToLogin(loginRequest)
//                    ?.collect {
//                        when (it) {
//                            is Resource.Success<*> -> {
//                                toast("Successful Login")
//                                findNavController().navigate(R.id.action_loginAccountFragment_to_homeFragment)
//                            }
//                            is Resource.Error -> {
//                                toast("Error occurred")
//                            }
//                        }
//                    }
//
//            }
//
//
//        }
//        }
//
//        return binding.root
//    }

}


//
//        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                loginViewModel.loginResult.collect {
//                    when (it) {
//                        is Resource.Success -> {
//                            binding.progressBar2.isVisible = false
//                            val userId = it.value.data
//
//                            it.value.data.let {
//                                if (it != null){
//                                    findNavController().navigate(R.id.action_loginAccountFragment_to_homeFragment)
//                                }
//                                else {
//                                    toast("Account Does not exist! Please create an account.")
//                                }
//                            }
//                        }
//                        is Resource.Error -> {
//                            toast("Error occurred...Please try again later")
//                        }
//                        is Resource.Loading -> {
//                            binding.progressBar2.isVisible = true
//                        }
//                    }
//
//                }
//            }
//        }
//    }

