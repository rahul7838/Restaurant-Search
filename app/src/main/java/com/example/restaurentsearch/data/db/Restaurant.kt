package com.example.restaurentsearch.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Restaurant")
data class Restaurant(
    @PrimaryKey
    val id: String,
    val name: String?
)