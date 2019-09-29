package com.example.restaurentsearch.ui

import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurentsearch.BaseFragment
import com.example.restaurentsearch.R
import com.example.restaurentsearch.RestaurantApplication
import com.example.restaurentsearch.component.PermissionFragment
import com.example.restaurentsearch.data.model.Result
import com.example.restaurentsearch.error.RestaurantErrorFragment
import com.example.restaurentsearch.extension.replace
import com.example.restaurentsearch.extension.visible
import kotlinx.android.synthetic.main.restaurant_list_fragment.*
import javax.inject.Inject

class RestaurantListFragment : BaseFragment(), RestaurantContract.View {

    private lateinit var adapter: RestaurantListAdapter

    @Inject
    lateinit var restaurantPresenter: RestaurantPresenter


    override fun showErrorScreen() {
        val restaurantErrorFragment = RestaurantErrorFragment.instance()
        replace(restaurantErrorFragment, R.id.container, false)
    }

    override fun showRestaurantList(restaurantList: ArrayList<Result>) {
        recycler_view_id.visible()
        adapter.updateRestaurantList()
    }

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
        return LayoutInflater.from(inflater.context)
            .inflate(R.layout.restaurant_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantPresenter.attachView(this)
        checkPermissionAndExecute()
        adapter = RestaurantListAdapter()
        recycler_view_id.adapter = adapter
        recycler_view_id.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun checkPermissionAndExecute() {
        activity?.let {
            Handler(Looper.getMainLooper()).post {
                PermissionFragment.getPermissionFragment(it)
                    .executeWithPermissionCheck(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        {
                            restaurantPresenter.getRestaurantList()
                        },
                        this::permissionDenialHandling
                    )
            }
        }
    }

    private fun permissionDenialHandling(boolean: Boolean) {
        Toast.makeText(context, "permission denied", Toast.LENGTH_LONG).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        restaurantPresenter.detachView()
    }

    companion object {
        val instance = RestaurantListFragment()
    }
}