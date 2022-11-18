package com.steve_md.testapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steve_md.testapp.data.remote.UserApiService
import com.steve_md.testapp.data.repositories.AuthUserRepository
import com.steve_md.testapp.data.repositories.AuthUserRepositoryImpl
import com.steve_md.testapp.data.requests.LoginRequest
import com.steve_md.testapp.data.requests.RegisterRequest
import com.steve_md.testapp.data.responses.LoginResponse
import com.steve_md.testapp.data.responses.RegisterResponse
import com.steve_md.testapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


class AuthViewModel : ViewModel() {

    /**
     * Since we aren't using dependency injection the only thing that can be passed
     * in the viewModel constructor which will not throw any error is savedStateHandle or application
     */
    private val authUserRepository: AuthUserRepository = AuthUserRepositoryImpl(UserApiService.getApiClient())

    /**
     * view model will communicate with repository
     * repository sends and gets data from the web server api
     * and then use the data to update the UI
     *
     * **/


    // use kotlin flows instead of live data

    /*
    * Method 1
    * This is changed to a mutable state flow, since we're not sharing it
    */

    // Login Observable

    private val _loginResult = MutableStateFlow<Resource<LoginResponse>?>(null)
    val loginResult: StateFlow<Resource<LoginResponse>?> get() = _loginResult

    // Register Observable
    private val _registerResult = MutableStateFlow<Resource<RegisterResponse>?>(null)
    val registerResult: StateFlow<Resource<RegisterResponse>?>
        get() = _registerResult


    // Login User
    fun loginUser(email: String, password: String) = viewModelScope.launch {
        val lResult = authUserRepository.userLogin(LoginRequest(email , password))
        _loginResult.emit(lResult)
    }

    // Register User
    fun registerUser(email: String, name: String, password: String) = viewModelScope.launch {
        _registerResult.value =
            authUserRepository.userRegister(
                registerRequest = RegisterRequest(
                    email,
                    name,
                    password
                )
            )
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

    /**
     * MAMBO's Implementation
     */

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginResult.value = authUserRepository.userLogin(LoginRequest(email = email, password = password))
    }
    fun register(email: String, name: String, password: String) = viewModelScope.launch {
        _registerResult.value = authUserRepository.userRegister(RegisterRequest(email = email, name = name, password = password))
    }



}