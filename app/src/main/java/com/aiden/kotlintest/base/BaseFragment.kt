package com.aiden.kotlintest.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment

abstract class BaseFragment: RxFragment() {

    protected lateinit var mInflater: LayoutInflater
    protected var mContainer: ViewGroup? = null
    private var mIsViewInit = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mInflater = inflater
        mContainer = container
        return initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!mIsViewInit && userVisibleHint) {
            initData()
            mIsViewInit = true
        }
    }

    abstract fun initView(): View

    abstract fun initData()
}