package com.example.restaurentsearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.restaurentsearch.BaseFragment
import com.example.restaurentsearch.R
import com.example.restaurentsearch.RestaurantApplication
import com.example.restaurentsearch.data.model.Result
import com.example.restaurentsearch.error.RestaurantErrorFragment
import com.example.restaurentsearch.extension.replace
import javax.inject.Inject

class RestaurantListFragment : BaseFragment(), RestaurantContract.View {

    override fun showErrorScreen() {
        val restaurantErrorFragment = RestaurantErrorFragment.instance()
        replace(restaurantErrorFragment, R.id.container, false)
    }

    override fun showRestaurantList(restaurantList: ArrayList<Result>) {
//        TODO
    }

    @Inject
    lateinit var restaurantPresenter: RestaurantPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RestaurantApplication.getInstance().restaurantComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return LayoutInflater.from(inflater.context).inflate(R.layout.restaurant_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantPresenter.attachView(this)
        restaurantPresenter.getRestaurantList()
    }



    override fun onDestroy() {
        super.onDestroy()
        restaurantPresenter.detachView()
    }
}