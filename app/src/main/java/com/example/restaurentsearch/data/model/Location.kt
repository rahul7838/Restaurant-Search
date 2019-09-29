package com.example.restaurentsearch.data.model

data class Location(
    val address: String,
    val cc: String,
    val city: String,
    val country: String,
    val formattedAddress: List<String>,
    val labeledLatLngs: List<LabeledLatLng>,
    val lat: Double,
    val lng: Double,
    val postalCode: String,
    val state: String
)