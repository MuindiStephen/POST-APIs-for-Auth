package com.steve_md.testapp.data.remote

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.steve_md.testapp.utils.Resource
import retrofit2.HttpException

/*
*  Safe APi Call through HTTP which is not secure
* */

open class SafeApiCall() {

    // use of coroutines for asynchronous programming -> without non blocking execution
    open suspend fun <T> safeApiCall(apiCall: suspend() -> T) : Resource<T> {

        /*
         * try and catch block functions with return body
         */

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