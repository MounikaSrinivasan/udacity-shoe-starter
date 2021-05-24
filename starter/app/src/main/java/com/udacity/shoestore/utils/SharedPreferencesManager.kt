package com.udacity.shoestore.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    private lateinit var prefs: SharedPreferences

    private const val PREFS_NAME = "shoeStore"

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun read(key: String, defautValue: String): String? {
        return prefs.getString(key, defautValue)
    }

    fun read(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }

    fun write(key: String, value: String) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putString(key, value)
            commit()
        }
    }

    fun write(key: String, value: Boolean) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putBoolean(key, value)
            commit()
        }
    }

    fun delete(key: String?) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            remove(key)
            commit()
        }
    }
}