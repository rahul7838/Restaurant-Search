package com.example.restaurentsearch

import android.app.Application
import com.example.restaurentsearch.depenedencyinjection.DaggerRestaurantComponent
import com.example.restaurentsearch.depenedencyinjection.RestaurantComponent
import dagger.Component

class RestaurantApplication : Application() {

    lateinit var restaurantComponent: RestaurantComponent

   init {
       initDagger()
   }

    private fun initDagger() {
        restaurantComponent = DaggerRestaurantComponent.create()
    }

    companion object {
        private var INSTANCE = RestaurantApplication()

        fun getInstance(): RestaurantApplication {
            return INSTANCE
        }
    }
}