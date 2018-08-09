package com.aiden.kotlintest.base

data class ListBean<T>(var total: Int, var hospitalList: ArrayList<T>, var page: Int, var limit: Int, var isFlag: Boolean, var ret_code: String) {
}
