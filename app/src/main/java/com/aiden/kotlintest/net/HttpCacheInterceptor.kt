package com.aiden.kotlintest.net

import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HttpCacheInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val oldRequest: Request = chain!!.request()
        val response: Response = chain.proceed(oldRequest)

        if (TextUtils.isEmpty(response.header("Cache-Control"))) {

            val maxAge = 60
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build()
        }
        return response
    }
}