package com.example.weathertoday.model.dataClass.forecast

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val description: String?,
    val icon: String?,
    val id: Double?,
    val main: String?
) : Parcelable