package com.example.restaurentsearch.depenedencyinjection

import android.content.Context
import com.example.restaurentsearch.ui.restaurantlist.RestaurantListFragment
import dagger.BindsInstance
import dagger.Component


@Component(modules = [RestaurantModule::class])
interface RestaurantComponent {

    fun inject(restaurantListFragment: RestaurantListFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(context: Context): Builder

        fun build(): RestaurantComponent
    }
}