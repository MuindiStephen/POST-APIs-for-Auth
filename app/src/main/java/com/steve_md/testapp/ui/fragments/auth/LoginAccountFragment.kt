package com.steve_md.testapp.ui.fragments.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.steve_md.testapp.R
import com.steve_md.testapp.data.requests.LoginRequest
import com.steve_md.testapp.data.responses.LoginResponse
import com.steve_md.testapp.databinding.FragmentLoginAccountBinding
import com.steve_md.testapp.utils.Resource
import com.steve_md.testapp.utils.toast
import com.steve_md.testapp.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class LoginAccountFragment : Fragment() {

    private lateinit var binding: FragmentLoginAccountBinding

    //private val loginViewModel: AuthViewModel by viewModels()
    private val loginViewModel = activity?.let {
        ViewModelProvider(it)
    }?.get(AuthViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginAccountBinding.inflate(layoutInflater, container, false)


        binding.dontHaveAccountSignUpTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginAccountFragment_to_createAccountFragment)
        }


        val loginRequest = LoginRequest(
            binding.inputEmail.editableText.toString(),
            binding.inputPassword.editableText.toString()
        )

        binding.buttonLogin.setOnClickListener {

            if (binding.inputEmail.text.isNullOrEmpty() && binding.inputPassword.text.isNullOrEmpty()) {
                toast("Enter credentials")
            }
            else {
            lifecycleScope.launch {
                loginViewModel?.postToLogin(loginRequest)
                    ?.collect {
                        when (it) {
                            is Resource.Success<*> -> {
                                toast("Successful Login")
                                findNavController().navigate(R.id.action_loginAccountFragment_to_homeFragment)
                            }
                            is Resource.Error -> {
                                toast("Error occurred")
                            }
                        }
                    }

            }


        }
        }

        return binding.root
    }

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

