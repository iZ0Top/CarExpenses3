package com.alex.carexpenses3.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_cars")
data class Car(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val brand: String,
    val model: String,
    val winCode: String,
    val purchaseDate: String,
    val year: Int,
    val fuelType: Int,
    val odometer: Int,
    val engineVolume: Double,
)