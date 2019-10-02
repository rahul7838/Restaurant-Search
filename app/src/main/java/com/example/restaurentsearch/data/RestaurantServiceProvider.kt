package com.example.restaurentsearch.data

import com.example.restaurentsearch.data.model.RestaurantResponse
import io.reactivex.Observable

interface RestaurantServiceProvider {

    fun getRestaurantResponse(ll: String, intent: String): Observable<RestaurantResponse>
}