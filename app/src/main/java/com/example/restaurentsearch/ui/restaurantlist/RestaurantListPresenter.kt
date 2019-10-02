package com.example.restaurentsearch.ui.restaurantlist

import com.example.restaurentsearch.data.DBRepository
import com.example.restaurentsearch.data.RestaurantServiceProvider
import com.example.restaurentsearch.data.model.RestaurantResponse
import com.example.restaurentsearch.data.model.Result
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class RestaurantListPresenter(
    private val restaurantServiceProvider: RestaurantServiceProvider,
    val dbRepository: DBRepository
) :
    RestaurantContract.Presenter {

    override var view: RestaurantContract.View? = null

    override var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getRestaurantList(ll: String) {
        view?.showLoading()
        restaurantServiceProvider.getRestaurantResponse(ll, "restaurant")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap(object : Function<RestaurantResponse, Observable<List<Result>>> {
                override fun apply(t: RestaurantResponse): Observable<List<Result>> {
                    return Observable.just(t.response.group.results)
                }
            })
            .flatMap { Observable.fromIterable(it) }
            .doOnEach {
                it.value?.venue?.let { venue ->
                    dbRepository.insertRestaurant(venue.id, venue.name)
                }
            }
            .toList()
            .subscribe({ success ->
                run {
                    handleSuccessResult(success)
                }
            },
                { error -> handleErrorResult(error) })
            .addTo(compositeDisposable)
    }

    private fun handleErrorResult(error: Throwable?) {
        view?.hideLoading()
        view?.showErrorScreen()
    }

    private fun handleSuccessResult(listOfResult: List<Result>?) {
        view?.filterRestaurantList(listOfResult as ArrayList<Result>)
    }
}