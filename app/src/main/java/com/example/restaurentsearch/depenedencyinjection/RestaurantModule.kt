package com.example.restaurentsearch.depenedencyinjection

import com.example.restaurentsearch.data.RestaurantServiceProvider
import com.example.restaurentsearch.data.RestaurantServiceProviderImpl
import com.example.restaurentsearch.ui.RestaurantPresenter
import dagger.Module
import dagger.Provides

@Module
class RestaurantModule {

    @Provides
    fun provideRestaurantServiceProvider(): RestaurantServiceProvider {
        return RestaurantServiceProviderImpl()
    }

    @Provides
    fun provideRestaurantPresenter(restaurantServiceProvider: RestaurantServiceProvider): RestaurantPresenter {
        return RestaurantPresenter(restaurantServiceProvider)
    }
}