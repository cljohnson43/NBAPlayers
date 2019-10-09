package com.example.nbaplayers.utils

import android.util.Log

object Logger {
    val DEBUG_TAG = "DEBUG"

    fun log(msg: String) = Log.d(DEBUG_TAG, msg)

}