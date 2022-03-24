package com.leboncoin.leboncoin

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LeBonCoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}