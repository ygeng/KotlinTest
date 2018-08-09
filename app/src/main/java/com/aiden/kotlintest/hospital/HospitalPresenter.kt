package com.aiden.kotlintest.hospital

import android.util.Log
import com.aiden.kotlintest.base.BaseResponse
import com.aiden.kotlintest.net.DefaultObserver
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
                .subscribe(object: DefaultObserver<BaseResponse<HospitalBean>>() {

                    override fun onSuccess(response: BaseResponse<HospitalBean>) {
                        hospitalView.refreshUI(response.showapi_res_body.hospitalList)
                    }

                    override fun onFailure(message: String?) {
                        hospitalView.loadFailed()
                    }

                })

    }

    override fun start() {
    }
}