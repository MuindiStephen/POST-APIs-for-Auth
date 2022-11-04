package com.steve_md.testapp.data.remote

import com.steve_md.testapp.data.requests.LoginRequest
import com.steve_md.testapp.data.requests.RegisterRequest
import com.steve_md.testapp.data.responses.LoginResponse
import com.steve_md.testapp.data.responses.RegisterResponse
import com.steve_md.testapp.utils.Constants.LOGIN_END_POINT
import com.steve_md.testapp.utils.Constants.REGISTER_END_POINT
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {

    // Register
    @POST(REGISTER_END_POINT)
    suspend fun registerUser(
        @Body registerRequest: RegisterRequest    // To directly control the request body
     ) : RegisterResponse

    // Login
    @POST(LOGIN_END_POINT)
    suspend fun loginUser(
        @Body loginRequest: LoginRequest         // To directly control the request body
    ) : LoginResponse
}