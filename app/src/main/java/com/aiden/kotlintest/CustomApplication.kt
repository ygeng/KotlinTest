package com.aiden.kotlintest

import android.app.Application
import com.aiden.kotlintest.db.DaoHelper
import com.tencent.bugly.Bugly

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        DaoHelper.initDatabase(this, "test.db")
        Bugly.init(this, "06a794358a", false)
    }

    companion object {
        lateinit var instance: CustomApplication
            private set
    }

}