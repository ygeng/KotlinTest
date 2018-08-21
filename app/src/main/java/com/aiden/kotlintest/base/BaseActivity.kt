package com.aiden.kotlintest.base

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import com.aiden.kotlintest.R
import com.tbruyelle.rxpermissions2.RxPermissions
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_toolbar.*

abstract class BaseActivity : RxAppCompatActivity() {

    protected lateinit var mContext: Activity
    private var mRxPermissions: RxPermissions? = null
    private var mPermissionDialog: AlertDialog? = null
    private lateinit var mPermissions: Array<String>
    private lateinit var mPermissionListener: OnRequestPermissionListener
    protected val REQEUST_CODE_PERMISSION = 0x100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_base)
        mContext = this
        setSupportActionBar(toolbar)

        ib_left.setOnClickListener { onLeftClicked() }
        ib_right.setOnClickListener { onRightClicked() }
        tv_right.setOnClickListener { onRightClicked() }

    }

    override fun setContentView(layoutResID: Int) {
        setContentView(View.inflate(mContext, layoutResID, null))
        initData()
    }

    override fun setContentView(view: View?) {
        ll_content.addView(view, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
    }

    protected abstract fun initData()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQEUST_CODE_PERMISSION) {
            // 从权限设置页面返回，重新请求权限
            requestPermission(permissions = *mPermissions, listener = mPermissionListener)
        }
    }

    /**
     * 设置标题栏
     * @author aiden@tronsis.com
     * @date 2018/8/21 17:21
     * @param showLeft 是否显示标题左边按钮（返回按钮），默认显示
     * @param title 标题
     * @param leftBtnRes 左边按钮背景图片资源id
     * @param rightBtnRes 右边按钮背景图片资源id
     * @param rightText 右边文字
     * @param rightTextRes 右边文字资源
     */
    protected fun setTitle(showLeft: Boolean = true, title: String, @DrawableRes leftBtnRes: Int = -1, @DrawableRes rightBtnRes: Int = -1, rightText: String = "", @StringRes rightTextRes: Int = -1) {
        if (!showLeft) {
            ib_left.visibility = View.GONE
        }
        tv_title.text = title
        if (leftBtnRes != -1) {
            ib_left.setImageResource(leftBtnRes)
        }
        if (rightBtnRes != -1) {
            ib_right.setImageResource(rightBtnRes)
            ib_right.visibility = View.VISIBLE
        }
        if (TextUtils.isEmpty(rightText)) {
            tv_right.text = rightText
            tv_right.visibility = View.VISIBLE
        }
        if (rightTextRes != -1) {
            tv_right.setText(rightTextRes)
            tv_right.visibility = View.VISIBLE
        }
    }

    protected fun hideToolbar() {
        toolbar.visibility = View.GONE
    }

    protected fun onLeftClicked() {
        finish()
    }

    protected fun onRightClicked() {

    }

    protected fun showError() {
        rl_error.visibility = View.VISIBLE
        ll_content.visibility = View.GONE
    }

    protected fun showContent() {
        rl_error.visibility = View.GONE
        ll_content.visibility = View.VISIBLE
    }

    protected fun requestPermission(vararg permissions: String, listener: OnRequestPermissionListener) {
        if (mRxPermissions == null) {
            mRxPermissions = RxPermissions(this)
        }

        mPermissions = permissions as Array<String>
        mPermissionListener = listener
        mRxPermissions!!.requestEachCombined(*permissions).subscribe { permission ->
            if (permission.granted) {
                // 获取到所有权限
                listener.onGranted()
            } else if (permission.shouldShowRequestPermissionRationale) {
                // 有至少一个权限被拒绝且没有勾选不再提示
                listener.onDenied()
            } else {
                // 有至少一个权限被拒绝且勾选不再提示
                listener.onDenied()
                showPermissionNoticeDialog()
            }
        }
    }

    /**
     * 拒绝并不再提示的权限 提示去设置页面设置
     */
    protected fun showPermissionNoticeDialog() {
        if (mPermissionDialog == null) {
            mPermissionDialog = AlertDialog.Builder(this)
                    .setMessage(R.string.permission_notice)
                    .setNegativeButton(R.string.cancel) { dialog, which -> }
                    .setPositiveButton(R.string.setting) { dialog, which ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivityForResult(intent, REQEUST_CODE_PERMISSION)
                    }
                    .create()
        }
        mPermissionDialog!!.show()
    }
}