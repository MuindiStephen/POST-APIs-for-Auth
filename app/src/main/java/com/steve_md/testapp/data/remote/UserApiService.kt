package com.steve_md.testapp.data.remote

import com.steve_md.testapp.data.requests.LoginRequest
import com.steve_md.testapp.data.requests.RegisterRequest
import com.steve_md.testapp.data.responses.LoginResponse
import com.steve_md.testapp.data.responses.RegisterResponse
import com.steve_md.testapp.utils.Constants.LOGIN_END_POINT
import com.steve_md.testapp.utils.Constants.REGISTER_END_POINT
import retrofit2.Call
import retrofit2.http.*


/*
*  API Interface
* */


interface UserApiService {

    // Register or SignUp
    @POST(REGISTER_END_POINT)
    suspend fun registerUser(
        @Body registerRequest: RegisterRequest    // To directly control the request
     ) : RegisterResponse


    // Login or Sign In
    @POST(LOGIN_END_POINT)
    suspend fun loginUser(
        @Body loginRequest: LoginRequest        // To directly control the request body
    ) : LoginResponse



//    @POST(VERIFY_END_POINT)
//    suspend fun verifyUser(
//        @Query("OTP") otp:String
//    )

    companion object{
        fun getApiClient(): UserApiService {
            return ApiClient.retrofit.create(UserApiService::class.java)
        }
    }
}