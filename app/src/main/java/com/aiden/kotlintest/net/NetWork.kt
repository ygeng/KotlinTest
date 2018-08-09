package com.aiden.kotlintest.net

import com.aiden.kotlintest.hospital.HospitalService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetWork {

    val BASE_URL = "http://route.showapi.com/"

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
            .client(OkHttpClient())
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
}