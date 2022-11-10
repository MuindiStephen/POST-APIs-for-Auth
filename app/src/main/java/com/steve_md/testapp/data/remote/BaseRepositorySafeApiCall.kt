package com.steve_md.testapp.data.remote

import com.steve_md.testapp.data.requests.LoginRequest
import com.steve_md.testapp.data.responses.LoginResponse
import com.steve_md.testapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/*
*  Safe APi Call through HTTP which is not secure
*
*  */

abstract class BaseRepositorySafeApiCall() {

    // use of coroutines for asynchronous programming -> without non blocking execution
     suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {

        /*
         * try and catch block functions with return body
         */


        //  Execute all the Api calls asynchronously by use of coroutines
        return withContext(Dispatchers.IO) {

            try {
                val response = apiCall.invoke()
                Resource.Success(response)
            } catch (throwable: Throwable) {
                when (throwable){
                    is HttpException -> {
                        Resource.Error(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Error(true,null, null)
                    }
                }
            }
        }

    }


}