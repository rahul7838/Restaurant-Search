package com.example.restaurentsearch

import android.app.Application
import com.example.restaurentsearch.depenedencyinjection.RestaurantComponent
import dagger.Component

class RestaurantApplication : Application() {

    lateinit var restaurantComponent: RestaurantComponent

    override fun onCreate() {
        super.onCreate()
    }

    fun initDagger() {
//        restaurantComponent = Dagge
    }


}