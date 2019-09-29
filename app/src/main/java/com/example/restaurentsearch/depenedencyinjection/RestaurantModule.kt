package com.example.restaurentsearch.depenedencyinjection

import com.example.restaurentsearch.data.RestaurantServiceProvider
import com.example.restaurentsearch.data.RestaurantServiceProviderImpl
import dagger.Module
import dagger.Provides

@Module
class RestaurantModule {

    @Provides
    fun provideRestaurantServiceProvider(): RestaurantServiceProvider {
        return RestaurantServiceProviderImpl()
    }
}