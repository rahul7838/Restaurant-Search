package com.example.restaurentsearch.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DislikeRestaurant::class, Restaurant::class],
    version = 1,
    exportSchema = false
)
abstract class RestaurantDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: RestaurantDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            RestaurantDatabase::class.java, "Restaurant.db"
        )
            .build()
    }

    abstract fun getRestaurantDao(): RestaurantDao
}
