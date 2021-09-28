package com.example.weathertoday.model.repository

import androidx.lifecycle.LiveData
import com.example.weathertoday.model.dataSource.current.CurrentWeatherLocalDataSource
import com.example.weathertoday.model.dataSource.current.CurrentWeatherRemoteDataSource
import com.example.weathertoday.model.db.entity.current.CurrentWeatherEntity
import io.reactivex.Completable
import javax.inject.Inject

class CurrentWeatherRepository @Inject constructor(
    private val remoteDataSource: CurrentWeatherRemoteDataSource,
    private val localDataSource: CurrentWeatherLocalDataSource
) : Repository {

    fun getCurrentWeather(cityName: String): Completable {
        return remoteDataSource.getCurrentWeather(cityName).doAfterSuccess {
            it.weather?.forEach { weather ->
                localDataSource.getNewCurrentWeather(
                    CurrentWeatherEntity(
                        dt = it.dt,
                        id = it.id,
                        cityName = it.name,
                        country = it.sys?.country,
                        weather_main = weather.main,
                        weather_icon = weather.icon,
                        weather_description = weather.description,
                        cloudiness_percent = it.clouds?.all,
                        feels_like = it.main?.feels_like,
                        humidity = it.main?.humidity,
                        windSpeed = it.wind?.speed,
                        temp = it.main?.temp,
                        temp_max = it.main?.temp_max,
                        temp_min = it.main?.temp_min
                    )
                )
            }
        }.ignoreElement()
    }

    fun getCurrentWeatherFromDao():LiveData<CurrentWeatherEntity>{
        return localDataSource.getDataFromDao()
    }
}