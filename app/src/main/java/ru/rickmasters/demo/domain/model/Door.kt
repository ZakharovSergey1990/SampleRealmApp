package ru.rickmasters.demo.domain.model

data class Door (
    val favorites: Boolean,
    val id: Int,
    val name: String,
    val room: String? = null,
    val snapshot: String?
)