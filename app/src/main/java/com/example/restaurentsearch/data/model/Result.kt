package com.example.restaurentsearch.data.model

data class Result(
    val displayType: String,
    val id: String,
    val photo: Photo,
    val snippets: Snippets,
    val venue: Venue
)