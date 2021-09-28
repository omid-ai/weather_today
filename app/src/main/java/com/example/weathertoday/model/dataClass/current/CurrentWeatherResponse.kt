package com.example.weathertoday.model.dataClass.current

data class CurrentWeatherResponse(
    val base: String?,
    val clouds: Clouds?,
    val cod: Int?,
    val dt: Int?,
    val id: Int?,
    val main: Main?,
    val name: String?,
    val sys: Sys?,
    val timezone: Int?,
    val weather: List<Weather>?,
    val wind: Wind?
)