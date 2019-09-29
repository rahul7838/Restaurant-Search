package com.example.restaurentsearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurentsearch.R
import com.example.restaurentsearch.error.RestaurantErrorFragment
import com.example.restaurentsearch.extension.add
import com.example.restaurentsearch.util.isNetworkAvailable

class RestaurantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant_activity_main)

        if (isNetworkAvailable(context = this)) {
            val restaurantFragment = RestaurantListFragment()
            add(restaurantFragment, R.id.container, false)
        } else {
            val restaurantErrorFragment = RestaurantErrorFragment()
            add(restaurantErrorFragment, R.id.container, false)
        }
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount >= 1){
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
