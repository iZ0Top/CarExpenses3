package com.alex.carexpenses3.utils

import com.alex.carexpenses3.MainActivity
import com.alex.carexpenses3.database.DatabaseRepository
import com.alex.carexpenses3.model.Car


lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: DatabaseRepository

var ODOMETER = 0
var CURRENT_CAR_ID = 0
lateinit var CURRENT_CAR: Car

const val PREF_NAME = "preferences_name"
const val DIALOG_ADD_TAG = "dialog_add_tag"
const val DIALOG_ADD_RESULT_KEY = "dialog_add_res_key"
const val DIALOG_ADD_REQUEST_KEY = "dialog_add_req_key"

const val TAG = "log"

