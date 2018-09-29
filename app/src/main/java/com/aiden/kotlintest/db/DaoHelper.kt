package com.aiden.kotlintest.db

import android.annotation.SuppressLint
import android.content.Context
import com.aiden.kotlintest.dao.DaoMaster
import com.aiden.kotlintest.dao.DaoSession
import com.aiden.kotlintest.dao.JokeBeanDao
import org.jetbrains.annotations.NotNull

object DaoHelper {

    private var mDaoSession: DaoSession? = null
    @SuppressLint("StaticFieldLeak")
    private var mOpenHelper: MyOpenHelper? = null

    fun initDatabase(context: Context, @NotNull databaseName: String) {
        closeConnections()
        mOpenHelper = MyOpenHelper(context, databaseName)
        mDaoSession = DaoMaster(mOpenHelper!!.writableDatabase).newSession()
    }

    fun closeConnections() {
        if (mOpenHelper != null) {
            mOpenHelper!!.close()
            mOpenHelper = null
        }
        if (mDaoSession != null) {
            mDaoSession!!.clear()
            mDaoSession = null
        }
    }

    fun clearDaoSession() {
        if (mDaoSession != null) {
            mDaoSession!!.clear()
            mDaoSession = null
        }
    }

    fun getJokeDao(): JokeBeanDao {
        return mDaoSession!!.jokeBeanDao
    }

}