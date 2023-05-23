package com.alex.carexpenses3.utils

import android.content.Context
import android.content.SharedPreferences

object Preferences {

    private lateinit var mPreferences: SharedPreferences

    fun getPreferences(context: Context){
        mPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    }

    fun setCurrentCarId(){

    }
    fun getCurrentCarId(){

    }




}
