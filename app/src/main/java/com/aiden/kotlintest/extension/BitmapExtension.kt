package com.aiden.kotlintest.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.*

fun Bitmap.toBytes(): ByteArray {
    val outputStream: ByteArrayOutputStream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    return outputStream.toByteArray()
}

fun Bitmap.save(directory: String, fileName: String): Boolean {
    var isSaved: Boolean = false
    val dir = File(directory)
    if(!dir.exists()) {
        dir.mkdirs()
    }
    val file = File(dir, fileName)
    if (file.exists()) {
        file.delete()
    }
    val fileOutPutStream = FileOutputStream(directory + File.separator + fileName)
    val bufferedOutPutStream = BufferedOutputStream(fileOutPutStream)
    isSaved = compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutPutStream)
    bufferedOutPutStream.flush()
    fileOutPutStream.flush()
    return isSaved
}

fun Bitmap.compress(): Bitmap {
    var options: Int = 100
    var baos = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.JPEG, 100, baos)
    while(baos.toByteArray().size / 1024 > 100) {
        baos.reset()
        options -= 10
        compress(Bitmap.CompressFormat.JPEG, options, baos)
        if(options < 10) {
            options = 10
        }
    }
    val byteArrayInputStream = ByteArrayInputStream(baos.toByteArray())
    return BitmapFactory.decodeStream(byteArrayInputStream)
}