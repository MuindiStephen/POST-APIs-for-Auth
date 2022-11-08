package com.steve_md.testapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steve_md.testapp.data.repositories.AuthUserRepositoryImpl
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
     * repository sends and gets data from the web server
     * and then use the data to update the UI
     *
     * **/


    // use kotlin flows instead of live data

    // Login Observable
    private val _loginResult = MutableSharedFlow<Resource<LoginResponse>>()


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



    fun loginUser(email:  String, password:String) = viewModelScope.launch(Dispatchers.Main) {
       // _loginResult.emit("")
    }


    // Register Observable
    private val registerResult = MutableSharedFlow<Resource<RegisterResponse>>()
    val _registerResult:SharedFlow<Resource<RegisterResponse>> = registerResult.asSharedFlow()


}