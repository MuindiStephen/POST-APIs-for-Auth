package com.steve_md.testapp.data.remote

import com.steve_md.testapp.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
*  API Client
* */

object ApiClient {
    // Sending data through Http
    var mHttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    var mOkHttpClient = OkHttpClient     // logs responses and requests
        .Builder()
        .addInterceptor(mHttpLoggingInterceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(mOkHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

//    val apiClient by lazy { retrofit.create(UserApiService::class.java) }
}