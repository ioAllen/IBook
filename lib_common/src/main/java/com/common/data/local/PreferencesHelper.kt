package com.common.data.local

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper private constructor(context: Context) {

    private val mPref: SharedPreferences

    init {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        checkPreferences()
    }

    private fun checkPreferences() {
        val oldVersion = mPref.getInt(KEY_PREFERENCES_VERSION, 1).toDouble()

        if (oldVersion < PREFERENCES_VERSION) {
            clear()
            val edit = mPref.edit()
            edit.putInt(KEY_PREFERENCES_VERSION, PREFERENCES_VERSION)
            edit.apply()
        }
    }

    fun clear() {
        mPref.edit().clear().apply()
    }

    private fun delete(key: String) {
        if (mPref.contains(key)) {
            mPref.edit().remove(key).apply()
        }
    }

    fun savePref(key: String, value: Any?) {
        delete(key)

        if (value is Boolean) {
            mPref.edit().putBoolean(key, (value as Boolean?)!!).apply()
        } else if (value is Int) {
            mPref.edit().putInt(key, (value as Int?)!!).apply()
        } else if (value is Float) {
            mPref.edit().putFloat(key, (value as Float?)!!).apply()
        } else if (value is Long) {
            mPref.edit().putLong(key, (value as Long?)!!).apply()
        } else if (value is String) {
            mPref.edit().putString(key, value as String?).apply()
        } else if (value is Enum<*>) {
            mPref.edit().putString(key, value.toString()).apply()
        } else if (value != null) {
            throw RuntimeException("Attempting to save non-primitive preference")
        }
    }

    fun <T> getPref(key: String): T {
        return mPref.all[key] as T
    }

    fun <T> getPref(key: String, defValue: T): T {
        val returnValue = mPref.all[key] as T
        return returnValue ?: defValue
    }

    companion object {

        private val PREF_FILE_NAME = "pref_file"

        private val KEY_PREFERENCES_VERSION = "key_preferences_version"
        private val PREFERENCES_VERSION = 1
        private var preferencesHelper: PreferencesHelper? = null

        fun getInstance(context: Context): PreferencesHelper {
            if (preferencesHelper == null) {
                preferencesHelper = PreferencesHelper(context)
            }
            return preferencesHelper!!
        }

        val instance: PreferencesHelper?
            get() = if (preferencesHelper != null) {
                preferencesHelper
            } else null
    }
}
