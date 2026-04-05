package com.abdulkarim.sharedpref

import android.content.Context
import androidx.core.content.edit

class SharedPrefHelper(application: Context) {
    private var sharedPreferences =
        application.getSharedPreferences("com.abdulkarim.sharedpref", 0)

    fun putString(key: String, value: String) {
        sharedPreferences.edit {
            putString(key, value)
        }
    }

    fun getString(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    fun putBool(key: String, value: Boolean) {
        sharedPreferences.edit {
            putBoolean(key, value)
        }
    }

    fun putInt(key: String, value: Int) {
        sharedPreferences.edit {
            putInt(key, value)
        }
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, -1)
    }

    fun clearAllCache() {
        sharedPreferences.edit { clear() }
    }
}