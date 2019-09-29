package com.example.restaurentsearch.data.model

data class Context(
    val currentLocation: CurrentLocation,
    val geoBounds: GeoBounds,
    val relatedNeighborhoods: List<Any>,
    val searchLocationMapBounds: Boolean,
    val searchLocationNearYou: Boolean,
    val searchLocationType: String
)