package com.alex.carexpenses3.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.carexpenses3.database.AppDatabase
import com.alex.carexpenses3.database.AppRepository
import com.alex.carexpenses3.model.Car
import com.alex.carexpenses3.utils.APP_ACTIVITY
import com.alex.carexpenses3.utils.REPOSITORY
import com.alex.carexpenses3.utils.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    val car = REPOSITORY.allCars
    val listEvents = REPOSITORY.allEvents
    val listExpenses = REPOSITORY.allExpenses

    init {
        Log.d(TAG, "ListViewModel.init")
        val dao = AppDatabase.getInstance(APP_ACTIVITY).getAppRoomDao()
        REPOSITORY = AppRepository(dao)

        if (car.value == null){
            addCarToDB(){
                Log.d(TAG, "ListViewModel.init.CarAdded")
            }
        }
    }

    fun getListExpenses(){

    }


    fun addCarToDB(onSuccess: () -> Unit){
        Log.d(TAG, "ListViewModel.addCarToDB")
        val car = Car(1, "Volkswagen", "Golf MK4", "WVV111222333444", "10-10-2019", 2001, 1, 175000, 1.4)
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.insertCar(car){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }







}