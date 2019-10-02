package com.example.restaurentsearch.data

import android.app.Activity
import android.content.Context
import com.example.restaurentsearch.data.db.DislikeRestaurant
import com.example.restaurentsearch.data.db.Restaurant
import com.example.restaurentsearch.data.db.RestaurantDatabase
import java.util.concurrent.Executors


class DBRepository(val context: Context) {

    private val diskIoExecutor = Executors.newSingleThreadExecutor()
    private val networkIoExecutor = Executors.newFixedThreadPool(5)

    private val db by lazy {
        RestaurantDatabase.invoke(context)
    }

    fun getFilterListOfRestaurant(job: (list: ArrayList<Restaurant>) -> Unit, activity: Activity) {
        diskIoExecutor.execute {
            val list = db.getRestaurantDao().getFilterListOfRestaurant()
            activity.runOnUiThread(Runnable { job.invoke(list as ArrayList<Restaurant>) })
        }
    }

    fun insertRestaurant(id: String, name: String?) {
        networkIoExecutor.execute {
            db.runInTransaction {
                db.getRestaurantDao().insertRestaurant(Restaurant(id, name))
            }
        }
    }

    fun deleteRestaurantTable() {
        diskIoExecutor.execute {
            db.getRestaurantDao().deleteRestaurant()
        }
    }

    fun insertDislikeRestaurant(dislikeRestaurant: DislikeRestaurant) {
        diskIoExecutor.execute {
            db.runInTransaction {
                db.getRestaurantDao().insertDislikeRestaurant(dislikeRestaurant)
            }
        }
    }
}