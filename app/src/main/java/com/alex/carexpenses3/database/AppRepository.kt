package com.alex.carexpenses3.database

import androidx.lifecycle.LiveData
import com.alex.carexpenses3.model.Car
import com.alex.carexpenses3.model.Event
import com.alex.carexpenses3.model.Expense

class AppRepository(private val appDao: AppDao): DatabaseRepository {

    override val allCars: LiveData<List<Car>>
        get() = TODO("Not yet implemented")

    override val allEvents: LiveData<List<Event>>
        get() = TODO("Not yet implemented")

    override val allExpenses: LiveData<List<Expense>>
        get() = TODO("Not yet implemented")

    override suspend fun insertCar(car: Car, onSuccess: () -> Unit) {
        appDao.insertCar(car)
        onSuccess()
    }

    override suspend fun insertEvent(event: Event, onSuccess: () -> Unit) {
        appDao.insertEvent(event)
        onSuccess()
    }

    override suspend fun insertExpense(expense: Expense, onSuccess: () -> Unit) {
        appDao.insertExpense(expense)
        onSuccess()
    }

    override suspend fun deleteCar(car: Car, onSuccess: () -> Unit) {
        appDao.deleteCar(car)
        onSuccess()
    }

    override suspend fun deleteEvent(event: Event, onSuccess: () -> Unit) {
        appDao.deleteEvent(event)
        onSuccess()
    }

    override suspend fun deleteExpense(expense: Expense, onSuccess: () -> Unit) {
        appDao.deleteExpense(expense)
        onSuccess()
    }
}