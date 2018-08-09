package com.aiden.kotlintest.net

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * 对请求到的数据做处理，重写了 {@link GsonResponseBodyConverter}，并重写相关调用类
 * @author aiden@tronsis.com
 * @date 2018/8/9 17:13
 */
class GsonConverterFactory(private var gson: Gson) : Converter.Factory() {
    /**
     * Create an instance using a default [Gson] instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
//    fun create(): GsonConverterFactory {
//        return create(Gson())
//    }

    /**
     * Create an instance using `gson` for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    // Guarding public API nullability.
//    fun create(gson: Gson?): GsonConverterFactory {
//        if (gson == null) throw NullPointerException("gson == null")
//        return GsonConverterFactory(gson)
//    }

    companion object {
        var factory: GsonConverterFactory? = null

        fun create(): GsonConverterFactory {
            if (factory == null) {
                synchronized(GsonConverterFactory::class) {
                    if (factory == null) {
                        factory = GsonConverterFactory(Gson())
                    }
                }
            }
            return factory!!
        }
    }

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>,
                                       retrofit: Retrofit): Converter<ResponseBody, *> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return GsonResponseBodyConverter(gson, adapter)
    }

    override fun requestBodyConverter(type: Type,
                                      parameterAnnotations: Array<Annotation>, methodAnnotations: Array<Annotation>, retrofit: Retrofit): Converter<*, RequestBody> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return GsonRequestBodyConverter(gson, adapter)
    }


}