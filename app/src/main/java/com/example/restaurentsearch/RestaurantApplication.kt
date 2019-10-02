package com.example.restaurentsearch

import android.app.Application
import com.facebook.stetho.Stetho

class RestaurantApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerInit.getInstance(this)
        Stetho.initializeWithDefaults(this)
    }
}

