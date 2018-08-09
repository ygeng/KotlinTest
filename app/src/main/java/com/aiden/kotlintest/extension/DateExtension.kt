package com.aiden.kotlintest.extension

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

val YMDHMS = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)

val YMD = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)

val HMS = SimpleDateFormat("HH:mm:ss", Locale.CHINA)

fun Date.format(dateFormat: DateFormat): String = dateFormat.format(this)

fun Date.formatYMDHMS(): String = format(YMDHMS)

fun Date.formatYMD(): String = format(YMD)

fun Date.formatHMS(): String = format(HMS)

