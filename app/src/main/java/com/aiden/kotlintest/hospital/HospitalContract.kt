package com.aiden.kotlintest.hospital

import com.aiden.kotlintest.base.BasePresenter
import com.aiden.kotlintest.base.BaseView
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent

interface HospitalContract {

    interface View : BaseView<Presenter> {
        fun refreshUI(hospitalList: ArrayList<HospitalBean>?)

        fun loadFailed()

        fun onFinish()
    }

    interface Presenter : BasePresenter {
        fun getHospitalList(cityName: String)
    }

    open class BaseLifecycleProvider(var provider: LifecycleProvider<ActivityEvent>)
}