package com.aiden.kotlintest.base

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    protected lateinit var mContext: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        mContext = this

        initData()
    }

    protected abstract fun getLayout(): Int

    protected abstract fun initData()

}