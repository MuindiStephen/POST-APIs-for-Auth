package com.steve_md.testapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.steve_md.testapp.R

/*
*  Session Manager to save and fetch the token on user's device
* */

class SessionManager (context: Context) {
    private var prefs : SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),
    Context.MODE_PRIVATE) // Preferences will only be accessible outside the app

    companion object {
        const val USER_TOKEN = "user_token"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)   // writing the token that does not exist
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)   // reading the token
    }

   fun clearToken() {
       return prefs.edit().clear().apply()
   }
}