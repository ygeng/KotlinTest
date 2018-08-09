package com.aiden.kotlintest.net

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter
/**
 * 对请求到的数据做处理，重写了 {@link GsonResponseBodyConverter}，并重写相关调用类
 * @author aiden@tronsis.com
 * @date 2018/8/9 17:13
 */
class GsonResponseBodyConverter<T>(var gson: Gson, var adapter: TypeAdapter<T>): Converter<ResponseBody, T> {

    override fun convert(value: ResponseBody?): T? {

        try {
            val response: BaseResponse<T> = adapter.fromJson(value!!.charStream()) as BaseResponse<T>

            if (response.code == 200) {
                return response.data
            } else {
                throw ServerResponseException(response.code, response.message)
            }
        } finally {
            value!!.close()
        }
    }
}