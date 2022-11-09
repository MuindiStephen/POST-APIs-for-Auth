package com.steve_md.testapp.viewmodel

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.steve_md.testapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

@HiltViewModel
class SplashViewModel: ViewModel () {
    // using livedata observable
    private val _value: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val value:LiveData<Boolean>
        get() = _value

    fun setValue() {
        Handler().postDelayed({
            _value.value = true
        }, 3500)
    }

}