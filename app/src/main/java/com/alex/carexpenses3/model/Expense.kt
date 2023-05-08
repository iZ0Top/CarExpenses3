package com.alex.carexpenses3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "table_expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var parent_id: Int,
    var type: Int,
    var odometer: Int,
    var quantity: Int,
    var price: Double,
    var date: String,
    var description: String,
    var detailDescription: String,
    var partNum: String,
): Serializable
