package com.example.restaurentsearch.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDislikeRestaurant(disLikeRestaurant: DislikeRestaurant)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRestaurant(restaurant: Restaurant)

    @Query("SELECT * FROM restaurant WHERE restaurant.id NOT IN ( SELECT disLikeRestaurant.id FROM disLikeRestaurant)")
    fun getFilterListOfRestaurant(): List<Restaurant>

    @Query("delete from Restaurant")
    fun deleteRestaurant()


    @Query("select * from DislikeRestaurant where id = :id")
    fun getDislikeRestaurant(id: String): DislikeRestaurant
}