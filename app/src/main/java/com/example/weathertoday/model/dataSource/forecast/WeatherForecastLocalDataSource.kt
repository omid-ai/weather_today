package com.example.weathertoday.model.dataSource.forecast

import androidx.lifecycle.LiveData
import com.example.weathertoday.model.db.dao.WeatherForecastDao
import com.example.weathertoday.model.db.entity.forecast.WeatherForecastEntity
import javax.inject.Inject

class WeatherForecastLocalDataSource @Inject constructor(
    private val weatherForecastDao: WeatherForecastDao
) {

    fun getDataFromDb():LiveData<WeatherForecastEntity> =
        weatherForecastDao.getWeatherForecastDataFromDao()

    fun getNewWeatherForecast(weatherForecastEntity: WeatherForecastEntity){
        weatherForecastDao.deleteExpiredData()
        weatherForecastDao.addNewDataToDb(weatherForecastEntity)
    }
}