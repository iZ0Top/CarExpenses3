package com.alex.carexpenses3.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alex.carexpenses3.model.Event
import com.alex.carexpenses3.model.Expense
import com.alex.carexpenses3.utils.LAST_ODOMETER
import com.alex.carexpenses3.utils.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class AddViewModel(application: Application): AndroidViewModel(application) {

    var currentId = 0L

    var eventLD = MutableLiveData<Event>()
    var listExpensesLD = MutableLiveData<List<Expense>>()

    private var event: Event
    private var listExpenses = mutableListOf<Expense>()



    init {
        val date = Date().toString()
        event = Event(odometer = LAST_ODOMETER, date = date)
        eventLD.postValue(event)
    }

    fun addExpenseToList(expense: Expense){
        listExpenses.add(expense)
        listExpensesLD.postValue(listExpenses)
    }

    fun deleteExpenseFromList(expense: Expense){
        listExpenses.remove(expense)
        listExpensesLD.postValue(listExpenses)
    }

    fun setNewOdometer(odometer: Int){
        event.odometer = odometer
        eventLD.postValue(event)
    }
    fun setNewDate(){

    }

    fun updateData(onSuccess: () -> Unit){
        for (x in listExpenses){
            x.date = event.date
            x.odometer = event.odometer
            x.parent_id = currentId.toInt()
        }
        onSuccess()
    }

    fun addEventToDB(onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            currentId = REPOSITORY.insertEvent(event) {
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }


    fun addExpensesListToDB(onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insertExpensesList(listExpenses) {
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }
}