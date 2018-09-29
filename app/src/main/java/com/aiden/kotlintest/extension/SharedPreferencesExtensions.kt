package com.aiden.kotlintest.extension

import android.content.Context
import android.content.SharedPreferences
import com.aiden.kotlintest.Constants

fun Context.sharedPreferences(): SharedPreferences {
    return getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
}

fun Context.putBoolean(key: String, value: Boolean): Boolean{
    val sharedPreferences = sharedPreferences()
    val editor = sharedPreferences.edit()
    editor.putBoolean(key, value)
    return editor.commit()
}

fun Context.getBoolean(key: String, default: Boolean = true): Boolean {
    return sharedPreferences().getBoolean(key, default)
}

fun Context.putInt(key: String, value: Int): Boolean {
    val sharedPreferences = sharedPreferences()
    val editor = sharedPreferences.edit()
    editor.putInt(key, value)
    return editor.commit()
}

fun Context.getInt(key: String, default: Int = -1): Int {
    return sharedPreferences().getInt(key, default)
}

fun Context.putString(key: String, value: String): Boolean {
    val sharedPreferences = sharedPreferences()
    val editor = sharedPreferences.edit()
    editor.putString(key, value)
    return editor.commit()
}

fun Context.getString(key: String, default: String = ""): String {
    return sharedPreferences().getString(key, default)
}

fun Context.putFloat(key: String, value: Float): Boolean {
    val sharedPreferences = sharedPreferences()
    val editor = sharedPreferences.edit()
    editor.putFloat(key, value)
    return editor.commit()
}

fun Context.getFloat(key: String, default: Float = -1f): Float {
    return sharedPreferences().getFloat(key, default)
}

fun Context.putLong(key: String, value: Long): Boolean {
    val sharedPreferences = sharedPreferences()
    val editor = sharedPreferences.edit()
    editor.putLong(key, value)
    return editor.commit()
}

fun Context.getLong(key: String, default: Long = -1L): Long {
    return sharedPreferences().getLong(key, default)
}