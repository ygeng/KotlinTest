package com.aiden.kotlintest.net

class BaseResponse<T> {

    var code: Int = 0
    lateinit var message: String
    var data: T? = null


}