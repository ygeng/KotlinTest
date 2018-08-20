package com.aiden.kotlintest.hospital

import com.aiden.kotlintest.base.BaseResponse
import com.aiden.kotlintest.hospital.HospitalBean
import com.aiden.kotlintest.net.NetConfig
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HospitalService {

    @FormUrlEncoded
    @POST(NetConfig.HOSIPITAL_LIST)
    fun hospitalList(@Field("cityName") cityName: String): Observable<BaseResponse<HospitalBean>> // @Field("showapi_appid") appId: String = NetConfig.APP_ID, @Field("showapi_sign") appSign: String = NetConfig.APP_SIGN,
}