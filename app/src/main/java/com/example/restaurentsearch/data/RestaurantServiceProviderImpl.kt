package com.example.restaurentsearch.data

import com.example.restaurentsearch.data.model.RestaurantResponse
import com.example.restaurentsearch.networking.RestaurantService
import com.example.restaurentsearch.networking.RestaurentApiClient
import io.reactivex.Observable

class RestaurantServiceProviderImpl : RestaurantServiceProvider {

    companion object {
        const val CLIENT_ID = "QOQ3WMOG3ITGEBH0QUNDFDIL43QDXVMVR5MJK0SEUOUN5WNU"
        const val CLIENT_SECRET = "4S2X5SHAWD51C0WDEZQLRNDPQMCEWC04ITZ1WQZYIYNRD4WV"
        const val LIMIT = "100"
        const val V = "20190920"
    }

    override fun getRestaurantResponse(ll: String, intent: String): Observable<RestaurantResponse> {
        val restaurantService =
            RestaurentApiClient().getRetrofit().create(RestaurantService::class.java)
        return restaurantService.getRestaurantResponse(
            CLIENT_ID,
            CLIENT_SECRET,
            ll,
            intent,
            LIMIT,
            V
        )
    }
}