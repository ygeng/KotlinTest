package com.aiden.kotlintest.net

import android.util.Log
import com.aiden.kotlintest.CustomApplication
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class NetWork {

    val BASE_URL = "http://route.showapi.com/"
    lateinit var mRetrofit: Retrofit

    init {
        mRetrofit = Retrofit.Builder()
                .client(getOkHttpClientBuilder().build())
                .baseUrl(NetConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun <T> getService(clazz: Class<T>): T = mRetrofit.create(clazz)

    companion object {
        var INSTANCE: NetWork? = null

        init {
            if (INSTANCE == null) {
                synchronized(NetWork::class) {
                    if (INSTANCE == null) {
                        INSTANCE = NetWork()
                    }
                }
            }
        }
    }

    private fun getOkHttpClientBuilder(): OkHttpClient.Builder {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            try {
                Log.e("OKHttp---", message)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("OKHttp---", message)
            }
        }

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