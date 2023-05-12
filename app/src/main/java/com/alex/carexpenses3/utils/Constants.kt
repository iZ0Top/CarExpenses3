package com.alex.carexpenses3.utils

import com.alex.carexpenses3.MainActivity
import com.alex.carexpenses3.database.DatabaseRepository
import com.alex.carexpenses3.model.Car


lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: DatabaseRepository

var ODOMETER = 0
var CURRENT_CAR_ID = 0
lateinit var CURRENT_CAR: Car

const val TAG = "log"

