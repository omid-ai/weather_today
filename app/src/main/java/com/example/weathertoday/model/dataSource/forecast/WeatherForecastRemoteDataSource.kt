package com.example.weathertoday.model.dataSource.forecast

import com.example.weathertoday.model.dataClass.forecast.WeatherForecastResponse
import com.example.weathertoday.service.ApiService
import io.reactivex.Single
import javax.inject.Inject

class WeatherForecastRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    fun getWeatherForecast(cityName:String): Single<WeatherForecastResponse> =
        apiService.fetchWeatherForecastData(cityName = cityName)
}