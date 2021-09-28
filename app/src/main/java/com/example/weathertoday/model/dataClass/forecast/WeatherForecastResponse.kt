package com.example.weathertoday.model.dataClass.forecast

import com.example.weathertoday.model.dataClass.forecast.City
import com.example.weathertoday.model.dataClass.forecast.Detail

data class WeatherForecastResponse(
    val city: City?,
    val cnt: Int?,
    val cod: String?,
    val list: List<Detail>?,
    val message: Int?
)