package com.steve_md.testapp.data.remote

import com.steve_md.testapp.data.requests.LoginRequest
import com.steve_md.testapp.data.requests.RegisterRequest
import com.steve_md.testapp.data.responses.LoginResponse
import com.steve_md.testapp.data.responses.RegisterResponse
import com.steve_md.testapp.utils.Constants.LOGIN_END_POINT
import com.steve_md.testapp.utils.Constants.REGISTER_END_POINT
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/*
*  API Interface
* */


interface UserApiService {

    // Register
    @FormUrlEncoded
    @POST(REGISTER_END_POINT)
    suspend fun registerUser(
        registerRequest: RegisterRequest    // To directly control the request
//        @Field("name") fullNames:String?,
//        @Field("id") ID:String?,
//        @Field("pass") email:String?,

     ) : RegisterResponse

    // Login
    @POST(LOGIN_END_POINT)
    suspend fun loginUser(
        @Body loginRequest: LoginRequest         // To directly control the request body
    ) : LoginResponse

    companion object{
        fun getApiClient(): UserApiService {
            return ApiClient.retrofit.create(UserApiService::class.java)
        }
    }
}