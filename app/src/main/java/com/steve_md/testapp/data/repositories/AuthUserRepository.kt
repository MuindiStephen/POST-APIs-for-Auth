package com.steve_md.testapp.data.repositories

import com.steve_md.testapp.data.requests.LoginRequest
import com.steve_md.testapp.data.requests.RegisterRequest
import com.steve_md.testapp.data.responses.LoginResponse
import com.steve_md.testapp.utils.Resource

class AuthUserRepository {

    // User Login
    suspend fun useLogin(loginRequest: LoginRequest) : Resource<LoginResponse>?{
        return Resource.Success()
    }

    //User Register
    suspend fun userRegister(registerRequest: RegisterRequest) : Resource<LoginResponse> {
        return Resource.Success()
    }
}