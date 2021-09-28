package com.example.weathertoday.model.dataClass.forecast

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Main(
    val feels_like: Double?,
    val grnd_level: Int?,
    val humidity: Int?,
    val pressure: Int?,
    val sea_level: Int?,
    val temp: Double?,
    val temp_kf: Double?,
    val temp_max: Double?,
    val temp_min: Double?
) : Parcelable{

    fun getTempString(): String {
        return temp.toString().substringBefore(".") + "°c"
    }

}