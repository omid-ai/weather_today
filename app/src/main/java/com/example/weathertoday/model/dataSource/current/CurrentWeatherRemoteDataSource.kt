package com.example.weathertoday.model.dataSource.current

import com.example.weathertoday.model.dataClass.current.CurrentWeatherResponse
import com.example.weathertoday.service.ApiService
import io.reactivex.Single
import javax.inject.Inject


class CurrentWeatherRemoteDataSource @Inject constructor(
    private val apiService: ApiService
){

    fun getCurrentWeather(cityName:String):Single<CurrentWeatherResponse>{
        return apiService.fetchCurrentWeatherData(cityName = cityName)
    }
}