package com.alex.carexpenses3.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_events")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val type: Int,
    val odometer: Int,
    val sum: Int,
    val date: String,
)
