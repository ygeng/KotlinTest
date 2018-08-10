package com.aiden.kotlintest.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.aiden.kotlintest.R
import com.aiden.kotlintest.extension.density


@SuppressLint("StaticFieldLeak")
object DialogUtils {

    private var mDialog: Dialog? = null
    private lateinit var mTvMessage: TextView
    private val mWidth = 160
    private val mHeight = 100

    @SuppressLint("InflateParams")
    fun showProgress(context: Context?, message: String) {
        if (mDialog == null) {
            mDialog = Dialog(context, R.style.LoadingDialog)

            val view = LayoutInflater.from(context).inflate(R.layout.dialog_loadding, null)
            mDialog!!.setContentView(view)
            mTvMessage = view.findViewById(R.id.tv_message)
            val window = mDialog!!.getWindow()
            val params = window.getAttributes()

            // set width,height by density and gravity
            val density = context!!.density()
            params.width = (mWidth * density).toInt()
            params.height = (mHeight * density).toInt()
            params.gravity = Gravity.CENTER

            window.attributes = params
        }
        if (TextUtils.isEmpty(message)) {
            mTvMessage.visibility = View.VISIBLE
            mTvMessage.text = message
        } else {
            mTvMessage.visibility = View.GONE
        }
        mDialog!!.show()
    }

    fun dismissProgress() {
        if (mDialog != null && mDialog!!.isShowing) {
            mDialog!!.dismiss()
        }
    }
}