package com.example.restaurentsearch.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * Dislike i
 */

@Entity(tableName = "Disliked restaurant")
data class DislikedRestaurant(
    @PrimaryKey
    val id: String,
    val dislike: Int)