package com.alex.carexpenses3.database

import androidx.lifecycle.LiveData
import com.alex.carexpenses3.model.Car
import com.alex.carexpenses3.model.Event
import com.alex.carexpenses3.model.Expense

interface DatabaseRepository {

    val allCars: LiveData<List<Car>>
    val allEvents: LiveData<List<Event>>
    val allExpenses: LiveData<List<Expense>>

    suspend fun insertCar(car: Car, onSuccess: () -> Unit)
    suspend fun insertEvent(event: Event, onSuccess: () -> Unit)
    suspend fun insertExpense(expense: Expense, onSuccess: () -> Unit)

    suspend fun deleteCar(car: Car, onSuccess: () -> Unit)
    suspend fun deleteEvent(event: Event, onSuccess: () -> Unit)
    suspend fun deleteExpense(expense: Expense, onSuccess: () -> Unit)

}