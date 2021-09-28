package com.example.weathertoday.model.dataClass.forecast

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sys(
    val pod: String?
) : Parcelable