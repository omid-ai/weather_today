package com.example.weathertoday.model.dataClass.forecast

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Detail(
    val clouds: Clouds?,
    val dt: Double?,
    val dt_txt: String?,
    val main: Main?,
    val pop: Double?,
    val sys: Sys?,
    val visibility: Int?,
    val weather: List<Weather>?,
    val wind: Wind?
) : Parcelable{

    fun getHourOfDay(): String {
        return dt_txt?.substringAfter(" ")?.substringBeforeLast(":") ?: "00:00"
    }

}
