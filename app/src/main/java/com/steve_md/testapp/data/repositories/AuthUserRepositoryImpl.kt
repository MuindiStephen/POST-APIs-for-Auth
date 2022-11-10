package com.steve_md.testapp.data.repositories

import com.steve_md.testapp.data.remote.BaseRepositorySafeApiCall
import com.steve_md.testapp.data.remote.UserApiService
import com.steve_md.testapp.data.requests.LoginRequest
import com.steve_md.testapp.data.requests.RegisterRequest
import com.steve_md.testapp.data.responses.LoginResponse
import com.steve_md.testapp.data.responses.RegisterResponse
import com.steve_md.testapp.utils.Resource
import java.io.IOException


class AuthUserRepositoryImpl(
    private val apiService: UserApiService
) : AuthUserRepository, BaseRepositorySafeApiCall () {

    // To implement the Interface [AuthUserRepository]


    // Login
    override suspend fun userLogin(email: String, password: String) = safeApiCall{
         apiService.loginUser(loginRequest = LoginRequest(email, password))
    }



    // Register or Sign Up
    override suspend fun userRegister(
        email: String,
        name: String,
        password: String
    ) = safeApiCall {
         val  registerUserRequest = RegisterRequest(
             email = email,
             name = name,
             password = password
         )

        apiService.registerUser(registerUserRequest)
    }


}










//    // User Login
//    override suspend fun userLogin(loginRequest:LoginRequest): Resource<LoginResponse> = safeApiCall{
//      //apiService.loginUser(loginRequest)
//        UserApiService.getApiClient().loginUser(loginRequest)
//    }
//
//
//    override suspend fun userRegister(registerRequest: RegisterRequest): Resource<RegisterResponse> = safeApiCall{
//        UserApiService.getApiClient().registerUser(registerRequest)
//    }
//
//    override suspend fun saveToken(token: String) {
//        TODO("Not yet implemented")
//    }


    //User Register
//    suspend fun userRegister(
//        registerRequest: RegisterRequest
//    ) : Resource<RegisterResponse>  {
//        Resource.Loading(null)
//        return try {
//            val registerResponse = UserApiService.getApiClient().registerUser(registerRequest)
//            Resource.Success(registerResponse)
//        } catch (e: IOException) {
//            return Resource.Error("Hey! Login Failed. Server is unreachable. Please check your internet connection" +
//                    " and try again...")
//        }
//    }
}