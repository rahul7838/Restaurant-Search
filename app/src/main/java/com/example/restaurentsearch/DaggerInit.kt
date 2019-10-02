package com.example.restaurentsearch

import android.content.Context
import com.example.restaurentsearch.depenedencyinjection.DaggerRestaurantComponent
import com.example.restaurentsearch.depenedencyinjection.RestaurantComponent

class DaggerInit private constructor(context: Context) {

    lateinit var restaurantComponent: RestaurantComponent


    init {
        initDagger(context)
    }

    private fun initDagger(context: Context) {
        restaurantComponent = DaggerRestaurantComponent
            .builder()
            .application(context)
            .build()
    }

    companion object {
        var instance: DaggerInit? = null

        fun getInstance(context: Context): DaggerInit =
            instance ?: DaggerInit(context).also {
                instance = it
            }
    }
}