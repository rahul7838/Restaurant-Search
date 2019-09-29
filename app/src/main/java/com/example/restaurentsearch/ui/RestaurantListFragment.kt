package com.example.restaurentsearch.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurentsearch.BaseFragment
import com.example.restaurentsearch.R
import com.example.restaurentsearch.RestaurantApplication
import com.example.restaurentsearch.component.PermissionFragment
import com.example.restaurentsearch.data.model.Result
import com.example.restaurentsearch.error.RestaurantErrorFragment
import com.example.restaurentsearch.extension.replace
import com.example.restaurentsearch.extension.visible
import com.example.restaurentsearch.ui.locationrequest.LocationRequestFragment
import kotlinx.android.synthetic.main.restaurant_list_fragment.*
import javax.inject.Inject

class RestaurantListFragment : BaseFragment(), RestaurantContract.View, LocationListener {
    override fun onLocationChanged(p0: Location?) {
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
    }

    override fun onProviderEnabled(p0: String?) {
    }

    override fun onProviderDisabled(p0: String?) {
    }

    private lateinit var adapter: RestaurantListAdapter

    @Inject
    lateinit var restaurantPresenter: RestaurantContract.Presenter


    override fun showErrorScreen() {
        val restaurantErrorFragment = RestaurantErrorFragment.instance()
        replace(restaurantErrorFragment, R.id.container, false)
    }

    override fun showRestaurantList(restaurantList: ArrayList<Result>) {
        recycler_view_id.visible()
        adapter.updateRestaurantList(restaurantList)
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
        recycler_view_id.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun checkPermissionAndExecute() {
        activity?.let {
            Handler(Looper.getMainLooper()).post {
                PermissionFragment.getPermissionFragment(it)
                    .executeWithPermissionCheck(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        {
                            getCurrentLocation()
                        },
                        this::permissionDenialHandling
                    )
            }
        }
    }

    private fun permissionDenialHandling(boolean: Boolean) {
        Toast.makeText(context, "permission denied", Toast.LENGTH_LONG).show()
    }

    private fun getCurrentLocation() {
        val locationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!isGPSEnable) {
            replace(LocationRequestFragment.instance, R.id.container, false)
        } else {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                == PackageManager.PERMISSION_GRANTED
            ) {
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    60000, 10f, this
                )
                val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

                val latitude = location?.latitude.toString()
                val longitude = location?.longitude.toString()
                restaurantPresenter.getRestaurantList("$latitude,$longitude")
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        restaurantPresenter.detachView()
    }

    companion object {
        val instance = RestaurantListFragment()
    }
}