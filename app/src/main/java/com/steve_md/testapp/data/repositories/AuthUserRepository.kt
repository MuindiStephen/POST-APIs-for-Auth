package com.steve_md.testapp.data.repositories

import com.steve_md.testapp.data.requests.*
import com.steve_md.testapp.data.responses.*
import com.steve_md.testapp.utils.Resource


// Communicates with remote data source (from web service/server)
interface AuthUserRepository {

    suspend fun userLogin(email:String, password: String) : Resource<LoginResponse>

    suspend fun userRegister(email:String, name:String, password: String) : Resource<RegisterResponse>

}