package com.steve_md.testapp.viewmodel

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SplashViewModel: ViewModel () {

    // Using Livedata observable

    private val _value: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val value:LiveData<Boolean>
        get() = _value

    fun setValue() {
        Handler().postDelayed({
            _value.value = true
        }, 3500)
    }

}