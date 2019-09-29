package com.example.restaurentsearch.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.restaurentsearch.R

class RestaurantErrorFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return LayoutInflater.from(context).inflate(R.layout.restaurant_error, container, false)
    }

    companion object {
        fun instance(): RestaurantErrorFragment {
            val fragment = RestaurantErrorFragment()
            fragment.arguments = Bundle().apply {
//                putParcelable(ERROR_MESSAGE_KEY, errorModel)
            }
            return fragment
        }
    }
}