package com.example.restaurentsearch.ui.locationrequest

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.restaurentsearch.R
import com.example.restaurentsearch.extension.replace
import com.example.restaurentsearch.ui.RestaurantListFragment
import kotlinx.android.synthetic.main.location_request_fragment.*

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
            replace(RestaurantListFragment.instance, R.id.container, false)
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode == 12)
//    }

    companion object {
        val instance = LocationRequestFragment()
    }
}