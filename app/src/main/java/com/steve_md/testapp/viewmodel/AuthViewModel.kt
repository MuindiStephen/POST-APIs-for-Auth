package com.steve_md.testapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steve_md.testapp.data.repositories.AuthUserRepository
import com.steve_md.testapp.data.responses.LoginResponse
import com.steve_md.testapp.data.responses.RegisterResponse
import com.steve_md.testapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthUserRepository
)  : ViewModel(){

    /**
     * view model will communicate with repository
     * repository sends and gets data from the web server
     * and then use the data to update the UI
     *
     * **/


    // use kotlin flows instead of live data

    // Login
    private val loginResult = MutableSharedFlow<Resource<LoginResponse>>()
    val _loginResult:SharedFlow<Resource<LoginResponse>> = loginResult.asSharedFlow()

    // Register
    private val registerResult = MutableSharedFlow<Resource<RegisterResponse>>()
    val _registerResult:SharedFlow<Resource<RegisterResponse>> = registerResult.asSharedFlow()

    fun loginUser() = viewModelScope.launch {
        return@launch
        repository
    }



}