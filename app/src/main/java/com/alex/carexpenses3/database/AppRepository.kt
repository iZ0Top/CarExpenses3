package com.alex.carexpenses3.database

import androidx.lifecycle.LiveData
import com.alex.carexpenses3.model.Car
import com.alex.carexpenses3.model.Event
import com.alex.carexpenses3.model.Expense

class AppRepository(private val appDao: AppDao): DatabaseRepository {

    override val allCars: LiveData<Car>
        get() = appDao.getAllCars()

    override val allEvents: LiveData<List<Event>>
        get() = appDao.getEvents()

    override val allExpenses: LiveData<List<Expense>>
        get() = appDao.getExpenses()

    override suspend fun insertCar(car: Car, onSuccess: () -> Unit) {
        appDao.insertCar(car)
        onSuccess()
    }

    override suspend fun insertEvent(event: Event, onSuccess: () -> Unit): Long {
        val x = appDao.insertEvent(event)
        onSuccess()
        return x
    }

    override suspend fun insertExpensesList(expensesList: List<Expense>, onSuccess: () -> Unit) {
        appDao.insertExpensesList(expensesList)
        onSuccess()
    }

    override suspend fun updateCar(car: Car, onSuccess: () -> Unit) {
        appDao.updateCar(car)
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