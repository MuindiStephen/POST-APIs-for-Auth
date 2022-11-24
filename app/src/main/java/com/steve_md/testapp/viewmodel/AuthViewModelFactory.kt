package com.steve_md.testapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.steve_md.testapp.data.repositories.AuthUserRepositoryImpl

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory (private val authUserRepository: AuthUserRepositoryImpl) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(AuthViewModel::class.java)){
           return AuthViewModel(authUserRepository) as T
       }
        throw java.lang.IllegalArgumentException("View model not found")

//        return super.create(modelClass)
    }
}