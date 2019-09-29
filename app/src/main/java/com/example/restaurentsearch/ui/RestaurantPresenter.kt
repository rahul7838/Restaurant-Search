package com.example.restaurentsearch.ui

import com.example.restaurentsearch.data.RestaurantServiceProvider
import com.example.restaurentsearch.data.model.RestaurantResponse
import com.example.restaurentsearch.data.model.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class RestaurantPresenter(private val restaurantServiceProvider: RestaurantServiceProvider) :
    RestaurantContract.Presenter {

    override var view: RestaurantContract.View? = null

    override var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getRestaurantList() {
        restaurantServiceProvider.getRestaurantResponse("dummy", "restaurant")
            .doOnSubscribe { view?.showLoading() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ success -> handleSuccessResult(success) },
                { error -> handleErrorResult(error)})
            .addTo(compositeDisposable)
    }

    private fun handleErrorResult(error: Throwable?) {
        view?.hideLoading()
        view?.showErrorScreen()
    }

    private fun handleSuccessResult(success: RestaurantResponse?) {
        view?.hideLoading()
        view?.showRestaurantList(success?.response?.group?.results as ArrayList<Result>)
    }


}