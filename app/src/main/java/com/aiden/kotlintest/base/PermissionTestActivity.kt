package com.aiden.kotlintest.base

import android.Manifest
import android.os.Bundle
import com.aiden.kotlintest.R
import com.aiden.kotlintest.extension.toast
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_permission.*

class PermissionTestActivity : BaseActivity() {

    lateinit var mRxPermissions: RxPermissions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
    }

    override fun initData() {
        setTitle(false, "权限")
        mRxPermissions = RxPermissions(this)

        btn_request.setOnClickListener {
            requestPermission(Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION,
                    listener = object : OnRequestPermissionListener {
                        override fun onGranted() {
                            toast("All permissions are granted")
                        }
                        override fun onDenied() {
                            toast("At least one denied permission without ask never again")
                        }
                    })
        }

        btn_request_each.setOnClickListener { requestEach(Manifest.permission.BLUETOOTH, Manifest.permission.CAMERA) }

        btn_request_each_combine.setOnClickListener { requestEachCombined(Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_FINE_LOCATION) }
    }

    private fun request(vararg permissions: String) {
        mRxPermissions.request(*permissions)
                .subscribe { granted ->
                    if (granted) {
                        toast("granted all permissions")
                    } else {
                        toast("some permissions denied")
                    }
                }
    }

    private fun requestEach(vararg permissions: String) {
        mRxPermissions.requestEach(*permissions)
                .subscribe { permission ->
                    if (permission.granted) {
                        toast("${permission.name} is granted")
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        toast("${permission.name} is denied without ask never again")
                    } else {
                        toast("${permission.name} is denied with ask never again")
                    }
                }
    }

    private fun requestEachCombined(vararg permissions: String) {
        mRxPermissions.requestEachCombined(*permissions)
                .subscribe { permission ->
                    if (permission.granted) {
                        toast("All permissions are granted")
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        toast("At least one denied permission without ask never again")
                    } else {
                        toast("At least one denied permission with ask never again")
                    }
                }
    }


}