package com.aiden.kotlintest.extension

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.support.annotation.ColorRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

val Context.screenHeight
    get() = resources.displayMetrics.heightPixels

val Context.TAG: String
    get() = this.javaClass.simpleName

fun Context.dp2px(value: Int): Int = (value * resources.displayMetrics.density).toInt()

fun Context.sp2px(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()

fun Context.px2dp(value: Int): Float = value.toFloat() / resources.displayMetrics.density

fun Context.px2sp(value: Int): Float = value.toFloat() / resources.displayMetrics.scaledDensity

fun Context.string(@StringRes resId: Int): String = getString(resId)

fun Context.color(@ColorRes resId: Int): Int = resources.getColor(resId)

fun Context.inflate(@LayoutRes resId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View = LayoutInflater.from(this).inflate(resId, parent, attachToRoot)

fun Context.getAppVersion(): String {
    val info = applicationContext.packageManager.getPackageInfo(applicationContext.packageName, 0)
    if (info != null) {
        return info.versionName
    }
    return ""
}

@SuppressLint("HardwareIds")
fun Context.getAndroidId(): String = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Context.toast(@StringRes stringResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(string(stringResId), duration)
}

