package com.aiden.kotlintest.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.aiden.kotlintest.dao.DaoMaster
import com.aiden.kotlintest.dao.JokeBeanDao

class MyOpenHelper(context: Context, name: String): DaoMaster.OpenHelper(context, name) {

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        if (newVersion > oldVersion) {
            MergeUpdateDBHelper.migrate(db, JokeBeanDao::class.java)
        }
    }
}