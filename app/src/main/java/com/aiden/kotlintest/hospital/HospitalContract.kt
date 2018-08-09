package com.aiden.kotlintest.hospital

import com.aiden.kotlintest.base.BasePresenter
import com.aiden.kotlintest.base.BaseView

interface HospitalContract {

    interface View: BaseView<Presenter> {
        fun refreshUI(hospitalList: ArrayList<HospitalBean>?)

        fun loadFailed()
    }

    interface Presenter: BasePresenter {
        fun getHospitalList(cityName: String)
    }
}