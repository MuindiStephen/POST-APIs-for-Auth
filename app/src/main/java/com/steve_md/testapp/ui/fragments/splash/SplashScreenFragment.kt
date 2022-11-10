package com.steve_md.testapp.ui.fragments.splash

import android.annotation.SuppressLint
import android.os.Build.VERSION_CODES.O
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.steve_md.testapp.R
import com.steve_md.testapp.databinding.FragmentSplashScreenBinding
import com.steve_md.testapp.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding
   // private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)

//        viewModel.value.observe(viewLifecycleOwner, Observer {
//            findNavController().navigate(R.id.action_splashScreenFragment_to_registerAccountFragment)
//        })
//
//       viewModel.setValue()

            Handler().postDelayed(
                {
                findNavController().navigate(R.id.action_splashScreenFragment_to_registerAccountFragment)
        },3000)


        return binding.root
    }

}