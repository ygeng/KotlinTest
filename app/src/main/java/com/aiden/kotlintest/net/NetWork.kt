package com.aiden.kotlintest.net

import android.util.Log
import com.aiden.kotlintest.CustomApplication
import com.aiden.kotlintest.hospital.HospitalService
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.util.concurrent.TimeUnit

class NetWork {

    val BASE_URL = "http://route.showapi.com/"

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
            .client(getOkHttpClientBuilder().build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun hospitalService(): HospitalService = getRetrofit().create(HospitalService::class.java)

    companion object {
        var netWork: NetWork? = null

        fun getInstance(): NetWork {
            if (netWork == null) {
                synchronized(NetWork::class) {
                    if (netWork == null) {
                        netWork = NetWork()
                    }
                }
            }
            return netWork!!
        }
    }

    fun getOkHttpClientBuilder(): OkHttpClient.Builder {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            try {
                Log.e("OKHttp---", message)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("OKHttp---", message)
            }
        })

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val cacheFile = File(CustomApplication.instance.cacheDir, "cache")
        val cache = Cache(cacheFile, 1024 * 1024 * 100)

        return OkHttpClient.Builder()
                .readTimeout(NetConfig.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(NetConfig.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(NetConfig.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(HttpHeaderInterceptor())
                .addNetworkInterceptor(HttpCacheInterceptor())
                .cache(cache)
    }
}