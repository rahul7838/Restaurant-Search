package com.example.restaurentsearch.data

interface RestaurantServiceProvider {

    fun getRestaurantResponse(ll: String, intent: String)
}