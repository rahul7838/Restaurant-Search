package com.example.restaurentsearch.ui.restaurantlist

import com.example.restaurentsearch.BaseContract
import com.example.restaurentsearch.data.model.Result

interface RestaurantContract {

    interface View : BaseContract.BaseView {
        fun filterRestaurantList(restaurantList: ArrayList<Result>)
        fun showErrorScreen()
    }

    interface Presenter : BaseContract.BasePresenter<View> {
        fun getRestaurantList(ll: String)
    }
}