package com.aiden.kotlintest.base

class BaseResponse<T>(var showapi_res_code: Int, var showapi_res_error: String, var showapi_res_body: ListBean<T>) {

    override fun toString(): String {
        return "BaseResponse(showapi_res_code=$showapi_res_code, showapi_res_error=$showapi_res_error, showapi_res_body=$showapi_res_body)"
    }
}
