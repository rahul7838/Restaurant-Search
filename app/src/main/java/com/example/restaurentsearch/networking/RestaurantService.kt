package com.example.restaurentsearch.networking

import com.example.restaurentsearch.data.model.RestaurantResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantService {

    @GET("search/recommendations")
    fun getRestaurantResponse(@Query("client_id")clientId: String,
                              @Query("client_secret")clientSecret: String,
                              @Query("ll")ll: String,
                              @Query("intent")intent: String,
                              @Query("limit")limit: String,
                              @Query("v")version: String): Single<RestaurantResponse>
}