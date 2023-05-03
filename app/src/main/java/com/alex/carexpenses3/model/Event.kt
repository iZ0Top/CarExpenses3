package com.alex.carexpenses3.model


data class Event(
    val id: Int,
    val type: Int,
    val odometer: Int,
    val sum: Int,
    val date: String,
)
