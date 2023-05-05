package com.alex.carexpenses3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alex.carexpenses3.model.Car
import com.alex.carexpenses3.model.Event
import com.alex.carexpenses3.model.Expense


@Database(entities = [Car::class, Event::class, Expense::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAppRoomDao(): AppDao

    companion object {
        @Volatile
        private var databse: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (databse == null) {
                databse = Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
                databse as AppDatabase
            } else {
                databse as AppDatabase
            }
            return databse as AppDatabase
        }
    }
}