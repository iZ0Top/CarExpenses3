package com.alex.carexpenses3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "table_expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
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
