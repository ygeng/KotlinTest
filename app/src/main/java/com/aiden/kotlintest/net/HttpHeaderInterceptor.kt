package com.aiden.kotlintest.net

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * add common parameters and headers
 * @author aiden@tronsis.com
 * @date 2018/8/9 15:08
 */
class HttpHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val oldRequest: Request = chain!!.request()

        val url: HttpUrl = oldRequest.url().newBuilder()
                .addQueryParameter("showapi_appid", NetConfig.APP_ID)
                .addQueryParameter("showapi_sign", NetConfig.APP_SIGN)
                .build()

        val requestBuilder: Request.Builder = oldRequest.newBuilder()
                .addHeader("Authorization", "token or other")
                .url(url)

        val newRequest: Request = requestBuilder.build()

        return chain.proceed(newRequest)
    }
}