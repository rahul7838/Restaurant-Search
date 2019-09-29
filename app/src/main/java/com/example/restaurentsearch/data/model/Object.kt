package com.example.restaurentsearch.data.model

data class Object(
    val agreeCount: Int,
    val canonicalUrl: String,
    val createdAt: Int,
    val disagreeCount: Int,
    val id: String,
    val logView: Boolean,
    val text: String,
    val todo: Todo,
    val type: String,
    val user: User
)