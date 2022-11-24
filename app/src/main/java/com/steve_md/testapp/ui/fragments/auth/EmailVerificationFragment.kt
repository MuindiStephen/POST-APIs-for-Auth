package com.steve_md.testapp.ui.fragments.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.steve_md.testapp.R
import com.steve_md.testapp.databinding.FragmentEmailVerificationBinding
import com.steve_md.testapp.utils.toast

class EmailVerificationFragment : Fragment() {

    // Safe View
   private var _binding: FragmentEmailVerificationBinding? = null
   private val binding get() = _binding!!

    // safe args
    private val args:EmailVerificationFragmentArgs by navArgs()
    private var email = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentEmailVerificationBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       //binding.pinView

        binding.buttonVerifyEmailCode.setOnClickListener {
            verifyEmailCode()
        }

        // Receive email string from Create account Fragment
        email = args.email
        binding.emailSentVerificationCode.text = email


    }

    private fun verifyEmailCode() {
        val inputEmailCode = binding.pinView.text.toString()
    // get the text and convert it to string

        if (inputEmailCode == "147258" ){
            toast("Email verification successful, Please login ")
            findNavController().navigate(R.id.action_emailVerificationFragment_to_loginAccountFragment)
        }
        else {
            toast("Invalid code, please try again")
        }



    }

}