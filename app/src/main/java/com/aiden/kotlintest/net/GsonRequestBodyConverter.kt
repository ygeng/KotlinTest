package com.aiden.kotlintest.net

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Converter
import java.io.OutputStreamWriter
import java.nio.charset.Charset
/**
 * 对请求到的数据做处理，重写了 {@link GsonResponseBodyConverter}，并重写相关调用类
 * @author aiden@tronsis.com
 * @date 2018/8/9 17:13
 */
class GsonRequestBodyConverter<T>(var gson: Gson, var adapter: TypeAdapter<T>) : Converter<T, RequestBody> {

    private val MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8")
    private val UTF_8 = Charset.forName("UTF-8")

    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
        val jsonWriter = gson.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString())
    }
}