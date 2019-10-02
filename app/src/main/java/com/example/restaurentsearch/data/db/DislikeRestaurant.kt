package com.example.restaurentsearch.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * Integer value of 0 is considered as dislike
 */

@Entity(tableName = "DislikeRestaurant")
data class DislikeRestaurant(
    @PrimaryKey
    val id: String,
    val dislike: Int)