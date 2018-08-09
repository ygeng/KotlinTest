package com.aiden.kotlintest.base

interface BaseView<T : BasePresenter> {

    fun setPresenter(presenter: T)
}