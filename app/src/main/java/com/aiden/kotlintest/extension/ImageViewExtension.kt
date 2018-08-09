package com.cgnpc.dnmc.extension

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.aiden.kotlintest.R
import com.aiden.kotlintest.extension.GlideApp
import com.aiden.kotlintest.extension.GlideRequest
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.io.File

fun ImageView.display(url: String, @DrawableRes placeholder: Int = R.drawable.default_img, @DrawableRes error: Int = R.drawable.default_img) {
    get(url).diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(placeholder)
            .error(error)
            .into(this)
}

fun ImageView.display(file: File, @DrawableRes placeholder: Int = R.drawable.default_img, @DrawableRes error: Int = R.drawable.default_img) {
    get(file).diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(placeholder)
            .error(error)
            .into(this)
}

fun ImageView.displayRound(url: String, @DrawableRes placeholder: Int = R.drawable.default_img, @DrawableRes error: Int = R.drawable.default_img) {
    get(url).diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(placeholder)
            .error(error)
//            .transform(RoundCornerTransform())
            .into(this)
}

fun ImageView.displayCircle(url: String, @DrawableRes placeholder: Int = R.drawable.default_img, @DrawableRes error: Int = R.drawable.default_img) {
//    get(url).diskCacheStrategy(DiskCacheStrategy.ALL)
//            .placeholder(placeholder)
//            .error(error)
//            .transform()
//            .into(this)
}

fun ImageView.displayDontTransform(url: String, @DrawableRes placeholder: Int = R.drawable.default_img, @DrawableRes error: Int = R.drawable.default_img) {
    get(url).diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontTransform()
            .placeholder(placeholder)
            .error(error)
            .into(this)
}

fun ImageView.get(url: String?): GlideRequest<Drawable> = GlideApp.with(context).load(url)

fun ImageView.get(file: File): GlideRequest<Drawable> = GlideApp.with(context).load(file)

class GlideWrapper {
    var url: String? = null
    var imageView: ImageView? = null
    var placeholder: Int = R.drawable.default_img
    var error: Int = R.drawable.default_img
    var transform: Transformation<Bitmap>? = null
}

fun ImageView.load(init: GlideWrapper.() -> Unit) {
    val wrap = GlideWrapper()
    wrap.init()
    execute(wrap)
}

private fun execute(wrapper: GlideWrapper) {
    wrapper.imageView?.let {
        var request = it.get(wrapper.url).placeholder(wrapper.placeholder).error(wrapper.error);
        if (wrapper.transform != null) {
            request.transform(wrapper.transform!!)
        }
        request.into(it)
    }
}