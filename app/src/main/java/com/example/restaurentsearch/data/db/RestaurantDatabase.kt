package com.example.restaurentsearch.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DislikedRestaurant::class], version = 1, exportSchema = false)
abstract class RestaurantDatabase : RoomDatabase() {
}