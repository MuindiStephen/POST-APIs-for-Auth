package com.steve_md.testapp.utils


//// Wrapping the API responses
//sealed class Resource <out T>{
//    data class Success<out T>(val data: T? = null) : Resource<T>()
//    data class Loading(val nothing: Nothing? = null) : Resource<Nothing>()
//    data class Error(val message: String?) :  Resource<Nothing>()
//    data class Empty(val unit: Unit?) : Resource<Unit>() // void  unit
//}

import okhttp3.ResponseBody

//Handle API Success and Error responses
sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Error(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}