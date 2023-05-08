package com.alex.carexpenses3.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_events")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: Int = 0,
    var odometer: Int = 0,
    val sum: Double = 0.0,
    val date: String = "",
)
