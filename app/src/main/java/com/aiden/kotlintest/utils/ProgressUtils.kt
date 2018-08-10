package com.aiden.kotlintest.utils

import android.app.Activity
import io.reactivex.ObservableTransformer
import java.lang.ref.WeakReference

object ProgressUtils {

    fun <T> applyProgressBar(activity: Activity, msg: String = ""): ObservableTransformer<T, T> {
        val activityWeakReference: WeakReference<Activity> = WeakReference(activity)
        val dialogUtil = DialogUtils
        dialogUtil.showProgress(activityWeakReference.get(), msg)
        return ObservableTransformer { upstream ->
            upstream.doOnSubscribe { }
                    .doOnTerminate {
                        val context: Activity? = activityWeakReference.get()
                        if (context != null && !context.isFinishing) {
                            dialogUtil.dismissProgress()
                        }
                    }.doOnSubscribe { }
        }
    }
}