package com.aiden.kotlintest.base

import android.app.Activity
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.text.TextUtils
import android.view.View
import com.aiden.kotlintest.R
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.layout_toolbar.*

abstract class BaseActivity : RxAppCompatActivity() {

    protected lateinit var mContext: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        mContext = this

        setSupportActionBar(toolbar)

        ll_content.addView(View.inflate(mContext, getLayout(), null))

        ib_left.setOnClickListener { onLeftClicked() }
        ib_right.setOnClickListener{ onRightClicked() }
        tv_right.setOnClickListener { onRightClicked() }

        initData()
    }

    protected abstract fun getLayout(): Int

    protected abstract fun initData()

    protected fun setTitle(showLeft: Boolean = true, title: String, @DrawableRes leftBtnRes: Int = -1, @DrawableRes rightBtnRes: Int = -1, rightText: String = "", @StringRes rightTextRes: Int = -1) {
        if (!showLeft) {
            ib_left.visibility = View.GONE
        }
        tv_title.text = title
        if (leftBtnRes != -1) {
            ib_left.setImageResource(leftBtnRes)
        }
        if (rightBtnRes != -1) {
            ib_right.setImageResource(rightBtnRes)
            ib_right.visibility = View.VISIBLE
        }
        if (TextUtils.isEmpty(rightText)) {
            tv_right.text = rightText
            tv_right.visibility = View.VISIBLE
        }
        if (rightTextRes != -1) {
            tv_right.setText(rightTextRes)
            tv_right.visibility = View.VISIBLE
        }
    }

    protected fun hideToolbar() {
        toolbar.visibility = View.GONE
    }

    protected fun onLeftClicked() {
        finish()
    }

    protected fun onRightClicked() {

    }
}