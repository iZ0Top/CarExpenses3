package com.alex.carexpenses3.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alex.carexpenses3.model.Expense

class AddViewModel(application: Application): AndroidViewModel(application) {

    private var list = MutableLiveData<List<Expense>>()


    fun addToList(){

    }
}