package com.aiden.kotlintest.base

import android.app.Activity
import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

abstract class BaseActivity: RxAppCompatActivity() {

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