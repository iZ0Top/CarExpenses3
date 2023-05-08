package com.alex.carexpenses3.utils

import com.alex.carexpenses3.MainActivity
import com.alex.carexpenses3.database.DatabaseRepository


lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: DatabaseRepository

var LAST_ODOMETER = 0
var CURRENT_CAR_ID = 0

const val TAG = "log"
