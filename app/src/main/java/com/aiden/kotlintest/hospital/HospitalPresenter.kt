package com.aiden.kotlintest.hospital

import android.util.Log
import com.aiden.kotlintest.base.BaseResponse
import com.aiden.kotlintest.net.NetWork
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HospitalPresenter(view: HospitalContract.View): HospitalContract.Presenter {
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
                .subscribe(object: Observer<BaseResponse<HospitalBean>> {
                    override fun onComplete() {
                        Log.e("HospitalPresenter", "onComplete")

                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.e("HospitalPresenter", "onSubscribe")
                    }

                    override fun onNext(t: BaseResponse<HospitalBean>) {
                        Log.e("HospitalPresenter", t.toString())
                        hospitalView.refreshUI(t.showapi_res_body.hospitalList)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("HospitalPresenter", "onError")
                        hospitalView.loadFailed()
                    }

                })

    }

    override fun start() {
    }
}