package com.alex.carexpenses3.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alex.carexpenses3.database.AppDatabase
import com.alex.carexpenses3.database.AppRepository
import com.alex.carexpenses3.utils.APP_ACTIVITY
import com.alex.carexpenses3.utils.REPOSITORY
import com.alex.carexpenses3.utils.TAG

class ListViewModel : ViewModel() {

    init {
        Log.d(TAG, "ListViewModel.init")
        val dao = AppDatabase.getInstance(APP_ACTIVITY).getAppRoomDao()
        REPOSITORY = AppRepository(dao)
    }

    val listEvents = REPOSITORY.allEvents
    val listExpenses = REPOSITORY.allExpenses







}