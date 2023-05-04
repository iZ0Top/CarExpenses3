package com.alex.carexpenses3.model

import java.io.Serializable

data class Expense(
    val id: Int,
    val type: Int,
    val odometer: Int,
    val quantity: Int,
    val price: Double,
    val date: String,
    val description: String,
    val detailDescription: String,
    val partNum: String,
): Serializable
