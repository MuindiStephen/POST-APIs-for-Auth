package com.steve_md.testapp.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Email
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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject



class AuthViewModel (
    private val authUserRepository: AuthUserRepositoryImpl
)  : ViewModel(){

    /**
     * view model will communicate with repository
     * repository sends and gets data from the web server api
     * and then use the data to update the UI
     *
     * **/


    // use kotlin flows instead of live data

    /*
    * Method 1*/

    // Login Observable
    private val _loginResult = MutableSharedFlow<Resource<LoginResponse>>()
    val loginResult:SharedFlow<Resource<LoginResponse>>
    get() = _loginResult

    // Register Observable
    private val _registerResult = MutableSharedFlow<Resource<RegisterResponse>>()
    val registerResult:SharedFlow<Resource<RegisterResponse>>
    get() = _registerResult


    // Login User
    fun loginUser(email: String, password: String) = viewModelScope.launch {
        val lResult = authUserRepository.userLogin(loginRequest = LoginRequest(email, password))
        _loginResult.emit(lResult)
    }

    // Register User
    fun registerUser(email: String, name:String, password: String) = viewModelScope.launch {
        _registerResult.emit(authUserRepository.userRegister(registerRequest = RegisterRequest(email, name, password)))
    }


  /*
  Method2**/

    fun postToLogin(loginRequest: LoginRequest) = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(loginRequest))
        } catch (e: Exception) {
            e.printStackTrace()  // throw the Exception
            emit(e.localizedMessage) // message type of the exception
        }
    }





}