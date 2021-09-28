package com.example.weathertoday.model.repository

import androidx.lifecycle.LiveData
import com.example.weathertoday.model.dataSource.forecast.WeatherForecastLocalDataSource
import com.example.weathertoday.model.dataSource.forecast.WeatherForecastRemoteDataSource
import com.example.weathertoday.model.db.entity.forecast.DetailList
import com.example.weathertoday.model.db.entity.forecast.WeatherForecastEntity
import io.reactivex.Completable
import javax.inject.Inject

class WeatherForecastRepository @Inject constructor(
    private val remoteDataSource: WeatherForecastRemoteDataSource,
    private val localDataSource: WeatherForecastLocalDataSource
) : Repository {

    fun getWeatherForecast(cityName: String): Completable {
        return remoteDataSource.getWeatherForecast(cityName).doAfterSuccess {
            it.list?.forEach { detail ->
                localDataSource.getNewWeatherForecast(
                    WeatherForecastEntity(
                        id = it.city?.id,
                        city = it.city?.name,
                        country = it.city?.country,
                        timeZone = it.city?.timezone,
                        sunrise = it.city?.sunrise,
                        sunset = it.city?.sunset,
                        detailList = DetailList(it.list)
                    )
                )
            }
        }.ignoreElement()
    }

    fun getWeatherForecastFromDb(): LiveData<WeatherForecastEntity> {
        return localDataSource.getDataFromDb()
    }
}