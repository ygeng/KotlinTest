package com.aiden.kotlintest.hospital

import java.io.Serializable

data class HospitalBean(var addr: String, var bus: String, var cityName: String, var hosName: String,
                        var img: String, var info: String, var provinceName: String, var tsks: String,
                        var keshi: String, var zzjb: String) : Serializable {
}
