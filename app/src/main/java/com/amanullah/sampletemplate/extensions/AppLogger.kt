package com.amanullah.sampletemplate.extensions

import android.util.Log
import com.amanullah.sampletemplate.BuildConfig

/**
 * Print a new debug log for development phase only.
 */
fun debugLog(log: String) {
    if (BuildConfig.DEBUG) {
        Log.d("AppLogger", "debugLog: $log")
    }
}

/**
 * Print a new error log for development phase only.
 */
fun errorLog(log: String) {
    if (BuildConfig.DEBUG) {
        Log.e("AppLogger", "errorLog: $log")
    }
}