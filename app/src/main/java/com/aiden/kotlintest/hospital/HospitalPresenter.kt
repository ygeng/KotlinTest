package com.aiden.kotlintest.hospital

import android.app.Activity
import android.content.Context
import com.aiden.kotlintest.base.BaseResponse
import com.aiden.kotlintest.net.DefaultObserver
import com.aiden.kotlintest.net.NetWork
import com.aiden.kotlintest.utils.ProgressUtils
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HospitalPresenter(var activity: Activity, view: HospitalContract.View, provider: LifecycleProvider<ActivityEvent>) : HospitalContract.Presenter, HospitalContract.BaseLifecycleProvider(provider) {
    var hospitalView: HospitalContract.View = view

    init {
        hospitalView.setPresenter(this)
    }

    override fun getHospitalList(cityName: String) {
        NetWork.getInstance()
                .hospitalService()
                .hospitalList(cityName = cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(provider.bindUntilEvent(ActivityEvent.DESTROY)) // 与activity生命周期绑定
                .compose(ProgressUtils.applyProgressBar(activity = activity)) // LoadingDialog显示
                .subscribe(object : DefaultObserver<BaseResponse<HospitalBean>>() {

                    override fun onSuccess(response: BaseResponse<HospitalBean>) {
                        hospitalView.refreshUI(response.showapi_res_body.hospitalList)
                    }

                    override fun onFailure(message: String?) {
                        hospitalView.loadFailed()
                    }

                    override fun onFinish() {
                        hospitalView.onFinish()
                    }

                })

    }

    override fun start() {
    }
}