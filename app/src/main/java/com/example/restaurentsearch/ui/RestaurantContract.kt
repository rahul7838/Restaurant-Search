package com.example.restaurentsearch.ui

import com.example.restaurentsearch.BaseContract

interface RestaurantContract {

    interface View : BaseContract.BaseView {

    }


    interface Presenter : BaseContract.BasePresenter<View> {
        fun getRestaurantList()
    }
}