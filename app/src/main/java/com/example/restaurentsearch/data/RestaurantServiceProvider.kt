package com.example.restaurentsearch.data

import com.example.restaurentsearch.data.model.RestaurantResponse
import io.reactivex.Single

interface RestaurantServiceProvider {

    fun getRestaurantResponse(ll: String, intent: String): Single<RestaurantResponse>
}