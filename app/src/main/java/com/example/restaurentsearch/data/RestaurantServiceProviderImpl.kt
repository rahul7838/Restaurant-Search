package com.example.restaurentsearch.data

import com.example.restaurentsearch.networking.RestaurantService
import com.example.restaurentsearch.networking.RestaurentApiClient

class RestaurantServiceProviderImpl : RestaurantServiceProvider {

    companion object {
        const val CLIENT_ID = "QOQ3WMOG3ITGEBH0QUNDFDIL43QDXVMVR5MJK0SEUOUN5WNU"
        const val CLIENT_SECRET = "4S2X5SHAWD51C0WDEZQLRNDPQMCEWC04ITZ1WQZYIYNRD4WV"
        const val LIMIT = "50"
        const val V = "20190920"


    }

    override fun getRestaurantResponse(ll: String, intent: String) {
        val restaurantService =
            RestaurentApiClient().getRetrofit().create(RestaurantService::class.java)
        restaurantService.getRestaurantResponse(
            CLIENT_ID,
            CLIENT_SECRET,
            ll,
            intent,
            LIMIT,
            V
        )
    }
}