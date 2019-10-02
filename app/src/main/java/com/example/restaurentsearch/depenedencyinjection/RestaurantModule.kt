package com.example.restaurentsearch.depenedencyinjection

import android.content.Context
import com.example.restaurentsearch.data.DBRepository
import com.example.restaurentsearch.data.RestaurantServiceProvider
import com.example.restaurentsearch.data.RestaurantServiceProviderImpl
import com.example.restaurentsearch.ui.restaurantlist.RestaurantContract
import com.example.restaurentsearch.ui.restaurantlist.RestaurantListPresenter
import dagger.Module
import dagger.Provides

@Module
class RestaurantModule {

    @Provides
    fun provideRestaurantServiceProvider(): RestaurantServiceProvider {
        return RestaurantServiceProviderImpl()
    }

    @Provides
    fun provideRestaurantPresenter(restaurantServiceProvider: RestaurantServiceProvider, dbRepository: DBRepository): RestaurantContract.Presenter {
        return RestaurantListPresenter(
            restaurantServiceProvider, dbRepository)
    }

    @Provides
    fun provideDbRepo(context: Context): DBRepository {
        return DBRepository(context)
    }
}