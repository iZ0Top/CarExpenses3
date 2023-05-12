package com.alex.carexpenses3.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alex.carexpenses3.model.Car
import com.alex.carexpenses3.model.Event
import com.alex.carexpenses3.model.Expense

@Dao
interface AppDao {

    @Query("SELECT * FROM table_cars WHERE id = 1")
    fun getAllCars(): LiveData<Car>

    @Query("SELECT * FROM table_events")
    fun getEvents(): LiveData<List<Event>>

    @Query("SELECT * FROM table_expenses")
    fun getExpenses(): LiveData<List<Expense>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: Car)
    @Insert
    suspend fun insertEvent(event: Event): Long
    @Insert
    suspend fun insertExpensesList(expensesList: List<Expense>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCar(car: Car)

    @Delete
    suspend fun deleteCar(car: Car)
    @Delete
    suspend fun deleteEvent(event: Event)
    @Delete
    suspend fun deleteExpense(expense: Expense)
}