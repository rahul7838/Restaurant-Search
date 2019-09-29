package com.example.restaurentsearch.data.model

data class CurrentLocation(
    val feature: Feature,
    val parents: List<Any>,
    val what: String,
    val `where`: String
)