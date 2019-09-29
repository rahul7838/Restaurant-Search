package com.example.restaurentsearch.depenedencyinjection

import com.example.restaurentsearch.ui.RestaurantListFragment
import dagger.Component

@Component(modules = [RestaurantModule::class])
interface RestaurantComponent {

    fun inject(restaurantListFragment: RestaurantListFragment)
}