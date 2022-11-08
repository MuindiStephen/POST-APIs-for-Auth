package com.steve_md.testapp.data.repositories

import com.steve_md.testapp.data.requests.LoginRequest
import com.steve_md.testapp.data.requests.RegisterRequest
import com.steve_md.testapp.data.responses.LoginResponse
import com.steve_md.testapp.data.responses.RegisterResponse
import com.steve_md.testapp.utils.Resource


// Communicates with remote data source (from web service/server)
interface AuthUserRepository {
    suspend fun userLogin(loginRequest: LoginRequest) : Resource<LoginResponse>
    suspend fun userRegister(registerRequest: RegisterRequest) : Resource<RegisterResponse>
    suspend fun saveToken(token:String)
}