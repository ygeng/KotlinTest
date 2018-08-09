package com.aiden.kotlintest.net

import java.lang.RuntimeException

class ServerResponseException(var code: Int, override var message: String?) : RuntimeException(message) {

    init {
        when(code) {
            400 -> message = "..."
            500 -> message = "..."
        }
    }

}