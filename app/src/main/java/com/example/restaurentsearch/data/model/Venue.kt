package com.example.restaurentsearch.data.model

data class Venue(
    val categories: List<Category>,
    val dislike: Boolean,
    val id: String,
    val location: Location,
    val name: String,
    val ok: Boolean,
    val venuePage: VenuePage
)