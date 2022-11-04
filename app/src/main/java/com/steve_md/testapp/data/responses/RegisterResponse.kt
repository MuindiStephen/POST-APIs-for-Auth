package com.steve_md.testapp.data.responses


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String
) {
    data class Data(
        @SerializedName("Email")
        val email: String,
        @SerializedName("Id")
        val id: Int,
        @SerializedName("Name")
        val name: String,
        @SerializedName("Token")
        val token: String
    )
}