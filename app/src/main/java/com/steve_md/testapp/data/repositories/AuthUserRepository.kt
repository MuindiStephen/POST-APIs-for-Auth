package com.steve_md.testapp.data.repositories

import com.steve_md.testapp.data.remote.ApiClient
import com.steve_md.testapp.data.remote.UserApiService
import com.steve_md.testapp.data.requests.LoginRequest
import com.steve_md.testapp.data.requests.RegisterRequest
import com.steve_md.testapp.data.responses.LoginResponse
import com.steve_md.testapp.data.responses.RegisterResponse
import com.steve_md.testapp.utils.Resource
import retrofit2.Response
import java.io.IOException

class AuthUserRepository (
    // private val apiService: UserApiService,
        ){

    // User Login
    suspend fun userLogin(loginRequest: LoginRequest) : Resource<Response<LoginResponse>>{
        Resource.Loading(null)
        return try {
         //  val loginResponse = apiService.loginUser(loginRequest = loginRequest)
            val loginResponse = UserApiService.getApiClient().loginUser(loginRequest)
            Resource.Success(loginResponse)
        } catch (e: IOException) {
            return Resource.Error("Hey! Login Failed. Server is unreachable. Please check your internet connection" +
                    " and try again...")
        }
    }

    //User Register
    suspend fun userRegister(registerRequest: RegisterRequest) : Resource<Response<RegisterResponse>> {
        Resource.Loading(null)
        return try {
            val registerResponse = UserApiService.getApiClient().registerUser(registerRequest)
            Resource.Success(registerResponse)
        } catch (e: IOException) {
            return Resource.Error("Hey! Login Failed. Server is unreachable. Please check your internet connection" +
                    " and try again...")
        }
    }
}