package com.alex.carexpenses3.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_cars")
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val brand: String,
    val model: String,
    val year: Int,
    val odometer: Int
)