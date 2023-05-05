package com.alex.carexpenses3.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alex.carexpenses3.model.Expense

class AddViewModel(application: Application): AndroidViewModel(application) {

    var listLD = MutableLiveData<List<Expense>>()
    private var list = mutableListOf<Expense>()


    fun addToList(expense: Expense){
        list.add(expense)
        listLD.postValue(list)
    }

    fun addToDB(){


    }


}