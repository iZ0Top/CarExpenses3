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
    private var currentEvent: Event
    private var listExpenses = mutableListOf<Expense>()
    private val dateFormatForDB = SimpleDateFormat("yyyy-MM-dd HH:MM:SS", Locale.US)

    init {
        Log.d(TAG, "AddViewModel.init")
        currentEvent = Event(odometer = ODOMETER, date = dateFormatForDB.format(Date()))
        eventLD.postValue(currentEvent)
    }

    fun addExpenseToList(expense: Expense){
        Log.d(TAG, "AddViewModel.addExpenseToList")
        listExpenses.add(expense)
        var sum = 0.0
        for (s in listExpenses){
            sum += (s.price * s.quantity)
        }
        currentEvent.sum = sum
        eventLD.postValue(currentEvent)
        listExpensesLD.postValue(listExpenses)
    }

    fun deleteExpenseFromList(expense: Expense){
        listExpenses.remove(expense)
        listExpensesLD.postValue(listExpenses)
    }

    fun setNewOdometer(odometer: Int){
        Log.d(TAG, "AddViewModel.setNewOdometer")
        currentEvent.odometer = odometer
        eventLD.postValue(currentEvent)
        ODOMETER = odometer
    }
    fun setNewDate(){

    }

    fun addEventToDB(onSuccess: () -> Unit){
        Log.d(TAG, "AddViewModel.addEventToDB")
        viewModelScope.launch(Dispatchers.IO) {
            currentId = REPOSITORY.insertEvent(currentEvent) {
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun updateData(onSuccess: () -> Unit){
        Log.d(TAG, "AddViewModel.updateData")
        for (x in listExpenses){
            x.date = currentEvent.date
            x.odometer = currentEvent.odometer
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

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "AddViewModel.onCleared")
    }

}