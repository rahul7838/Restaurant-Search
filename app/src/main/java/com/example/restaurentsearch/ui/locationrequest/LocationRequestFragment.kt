package com.example.restaurentsearch.ui.locationrequest

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.restaurentsearch.R
import com.example.restaurentsearch.extension.replace
import com.example.restaurentsearch.ui.restaurantlist.RestaurantListFragment
import kotlinx.android.synthetic.main.location_request_fragment.*
/*
 *Log is used for debug purpose will remove on later stage
 */
class LocationRequestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return LayoutInflater.from(inflater.context).inflate(R.layout.location_request_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_turn_on.setOnClickListener {
            requireActivity().startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")

        val locationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (isGPSEnable) {
            replace(RestaurantListFragment.instance, R.id.container, false)
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }

    companion object {
        val instance = LocationRequestFragment()
        private val TAG = LocationRequestFragment::class.java.canonicalName

    }
}