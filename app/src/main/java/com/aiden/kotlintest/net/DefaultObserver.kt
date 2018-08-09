package com.aiden.kotlintest.net

import android.net.ParseException
import android.util.Log
import com.aiden.kotlintest.CustomApplication
import com.aiden.kotlintest.extension.toast
import com.google.gson.JsonParseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException

abstract class DefaultObserver<T> : Observer<T> {
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
        onSuccess(t)
        onFinish()
    }

    override fun onError(e: Throwable) {
        Log.e("Response", e.message)
            if (e is HttpException) {
                onException(ExceptionReason.BAD_NETWORK)
            } else if (e is ConnectException || e is UnknownHostException) {
                onException(ExceptionReason.CONNECT_ERROR)
            } else if (e is InterruptedIOException) {
                onException(ExceptionReason.CONNECT_TIMEOUT)
            } else if (e is JsonParseException || e is JSONException || e is ParseException) {
                onException(ExceptionReason.PARSE_ERROR)
            } else if (e is ServerResponseException) {
                onFailure(e.message)
            } else {
                onException(ExceptionReason.UNKNOWN_ERROR)
            }
        onFinish()
    }

    public abstract fun onSuccess(response: T)

    public abstract fun onFailure(message: String?)

    public fun onFinish() {}

    public fun onException(reason: ExceptionReason) {
        when (reason) {
            ExceptionReason.PARSE_ERROR -> CustomApplication.instance.toast("解析数据失败")
            ExceptionReason.BAD_NETWORK -> CustomApplication.instance.toast("网络不给力")
            ExceptionReason.CONNECT_ERROR -> CustomApplication.instance.toast("连接失败")
            ExceptionReason.CONNECT_TIMEOUT -> CustomApplication.instance.toast("连接超时")
            ExceptionReason.UNKNOWN_ERROR -> CustomApplication.instance.toast("未知错误")
        }
    }

    enum class ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR
    }
}