package com.steve_md.testapp.data.remote

import com.steve_md.testapp.utils.Resource
import retrofit2.HttpException

/*
*  Safe APi Call through HTTP which is not secure
* */

open class SafeApiCall {
    open suspend fun <T> safeApiCall(apiCall: suspend () -> T) : Resource<T> {
        return try {
             Resource.Success(apiCall.invoke())
        }
        catch (throwable : Throwable) {
            when (throwable){
                is HttpException -> {
                    Resource.Error(throwable.response()?.errorBody().toString())
                }
                else -> {
                    Resource.Error(throwable.localizedMessage)
                }
            }
        }
    }
}