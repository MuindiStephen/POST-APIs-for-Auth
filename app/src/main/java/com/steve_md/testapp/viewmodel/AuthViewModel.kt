package com.steve_md.testapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.steve_md.testapp.data.responses.LoginResponse
import com.steve_md.testapp.utils.Resource

class AuthViewModel {
    val loginResult : MutableLiveData<Resource<LoginResponse>> = MutableLiveData<Resource<LoginResponse>>()
}