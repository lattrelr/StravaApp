package com.ryanl.stravasramapp

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object StoredAppPrefs {
    private const val SHARED_PREFS_NAME = "MySharedPrefs"
    private var prefs: SharedPreferences? = null

    fun open(context: Context) {
        prefs ?: run {
            prefs = context.getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE)
        }
    }

    private fun getString(key: String): String {
        var ret = ""
        prefs?.let {
            it.getString(key, "")?.let { value ->
                ret = value
            }
        }
        return ret
    }

    private fun getLong(key: String): Long {
        var ret: Long = -1
        prefs?.let {
            ret = it.getLong(key, -1)
        }
        return ret
    }

    private fun putString(key: String, value: String) {
        val editor: SharedPreferences.Editor? = prefs?.edit()
        editor?.let {
            editor.putString(key, value)
            editor.apply()
        }
    }

    private fun putLong(key: String, value: Long) {
        val editor: SharedPreferences.Editor? = prefs?.edit()
        editor?.let {
            editor.putLong(key, value)
            editor.apply()
        }
    }

    fun getToken(): String {
        return getString("token")
    }

    fun setToken(token: String) {
        putString("token", token)
    }

    fun getExpires(): Long {
        return getLong("expires")
    }

    fun setExpires(expires: Long) {
        putLong("expires", expires)
    }
}