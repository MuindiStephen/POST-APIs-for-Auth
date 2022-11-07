package com.steve_md.testapp.utils

sealed class Resource <out T>{
    data class Success<out T>(val data: T? = null) : Resource<T>()
    data class Loading(val nothing: Nothing? = null) : Resource<Nothing>()
    data class Error(val message: String?) :  Resource<Nothing>()
    data class Empty(val unit: Unit?) : Resource<Unit>() // void  unit
}