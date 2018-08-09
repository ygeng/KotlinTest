package com.aiden.kotlintest

import android.app.Application

class CustomApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: CustomApplication
            private set
    }
}