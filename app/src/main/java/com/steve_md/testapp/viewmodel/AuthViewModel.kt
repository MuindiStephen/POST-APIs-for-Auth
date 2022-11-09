package com.steve_md.testapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steve_md.testapp.data.repositories.AuthUserRepositoryImpl
import com.steve_md.testapp.data.requests.LoginRequest
import com.steve_md.testapp.data.requests.RegisterRequest
import com.steve_md.testapp.data.responses.LoginResponse
import com.steve_md.testapp.data.responses.RegisterResponse
import com.steve_md.testapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthUserRepositoryImpl
)  : ViewModel(){

    /**
     * view model will communicate with repository
     * repository sends and gets data from the web server api
     * and then use the data to update the UI
     *
     * **/


    // use kotlin flows instead of live data



//    private val _emailStatus = MutableSharedFlow<String>()
//    val emailStatus:SharedFlow<String> = _emailStatus.asSharedFlow()
//
//    private val _passwordStatus = MutableSharedFlow<String>()
//    val passwordStatus:SharedFlow<String> =_passwordStatus.asSharedFlow()
//
////    fun setEmail(value: String){
////        _emailStatus.asSharedFlow()
////    }
////
////     fun setPassword(value: String) {
////        _passwordStatus. asSharedFlow()
////     }

    // Login Observable
    private val _loginResult = MutableSharedFlow<Resource<LoginResponse>>()
    val loginResult:SharedFlow<Resource<LoginResponse>>
    get() = _loginResult

    // Register Observable
    private val _registerResult = MutableSharedFlow<Resource<RegisterResponse>>()
    val registerResult:SharedFlow<Resource<RegisterResponse>>
    get() = _registerResult


    // Login User
    fun loginUser(loginRequest: LoginRequest) = viewModelScope.launch(Dispatchers.Main) {
        val result = repository.userLogin(loginRequest = loginRequest)
        _loginResult.emit(result)
    }

    // Register User
    fun registerUser(registerRequest: RegisterRequest) =
        viewModelScope.launch {
           val result2 = repository.userRegister(registerRequest = registerRequest)
            _registerResult.emit(result2)
        }



}