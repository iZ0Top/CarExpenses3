package com.alex.carexpenses3.ui.add

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alex.carexpenses3.model.Event
import com.alex.carexpenses3.model.Expense
import com.alex.carexpenses3.utils.ODOMETER
import com.alex.carexpenses3.utils.REPOSITORY
import com.alex.carexpenses3.utils.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddViewModel(application: Application): AndroidViewModel(application) {

    var currentId = 0L

    var eventLD = MutableLiveData<Event>()
    var listExpensesLD = MutableLiveData<List<Expense>>()
    private var event: Event
    private var listExpenses = mutableListOf<Expense>()
    private val dateFormatForDB = SimpleDateFormat("yyyy-MM-dd HH:MM:SS", Locale.US)

    init {
        Log.d(TAG, "AddViewModel.init")
        event = Event(odometer = ODOMETER, date = dateFormatForDB.format(Date()))
        eventLD.postValue(event)
    }

    fun addExpenseToList(expense: Expense){
        Log.d(TAG, "AddViewModel.addExpenseToList")
        listExpenses.add(expense)
        var sum = 0.0
        for (s in listExpenses){
            sum += (s.price * s.quantity)
        }
        event.sum = sum
        eventLD.postValue(event)
        listExpensesLD.postValue(listExpenses)
    }

    fun deleteExpenseFromList(expense: Expense){
        listExpenses.remove(expense)
        listExpensesLD.postValue(listExpenses)
    }

    fun setNewOdometer(odometer: Int){
        Log.d(TAG, "AddViewModel.setNewOdometer")
        event.odometer = odometer
        eventLD.postValue(event)
        ODOMETER = odometer
    }
    fun setNewDate(){

    }

    fun addEventToDB(onSuccess: () -> Unit){
        Log.d(TAG, "AddViewModel.addEventToDB")
        viewModelScope.launch(Dispatchers.IO) {
            currentId = REPOSITORY.insertEvent(event) {
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun updateData(onSuccess: () -> Unit){
        Log.d(TAG, "AddViewModel.updateData")
        for (x in listExpenses){
            x.date = event.date
            x.odometer = event.odometer
            x.parent_id = currentId.toInt()
        }
        onSuccess()
    }

    fun addExpensesListToDB(onSuccess:() -> Unit){
        Log.d(TAG, "AddViewModel.addExpensesListToDB")
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insertExpensesList(listExpenses) {
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                    onCleared()
                }
            }
        }
    }
}