package com.aiden.kotlintest.hospital

import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id
import org.greenrobot.greendao.annotation.Unique
import java.io.Serializable

@Entity
data class HospitalBean(@Id(autoincrement = true) var id: Long, var addr: String, var bus: String, var cityName: String, @Unique var hosName: String,
                        var img: String, var info: String, var provinceName: String, var tsks: String,
                        var keshi: String, var zzjb: String) : Serializable {
}
