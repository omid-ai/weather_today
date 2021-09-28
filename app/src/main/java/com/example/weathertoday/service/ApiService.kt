package com.example.weathertoday.service

import com.example.weathertoday.model.dataClass.current.CurrentWeatherResponse
import com.example.weathertoday.model.dataClass.forecast.WeatherForecastResponse
import com.example.weathertoday.util.Variables.CURRENT_WEATHER_ACCESS_KEY
import com.example.weathertoday.util.Variables.FORECAST_WEATHER_ACCESS_KEY
import com.example.weathertoday.util.Variables.UNIT
import com.example.weathertoday.util.Variables.UNITS
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    fun fetchCurrentWeatherData(
        @Query("appid")appId:String=CURRENT_WEATHER_ACCESS_KEY,
        @Query("q")cityName:String,
        @Query("units")units:String=UNITS
    ): Single<CurrentWeatherResponse>

    @GET("forecast")
    fun fetchWeatherForecastData(
        @Query("q")cityName:String,
        @Query("appid")accessKey:String=FORECAST_WEATHER_ACCESS_KEY,
        @Query("unites")unit:String= UNITS
    ):Single<WeatherForecastResponse>
}