package com.steve_md.testapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class HiltApp : Application(){
    // Timber for logging
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}