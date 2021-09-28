package com.example.weathertoday.model.dataSource.current

import androidx.lifecycle.LiveData
import com.example.weathertoday.model.db.dao.CurrentWeatherDao
import com.example.weathertoday.model.db.entity.current.CurrentWeatherEntity
import javax.inject.Inject

class CurrentWeatherLocalDataSource @Inject constructor(
    private val currentWeatherDao: CurrentWeatherDao
) {

    fun getDataFromDao():LiveData<CurrentWeatherEntity>{
        return currentWeatherDao.getCurrentWeatherFromDb()
    }

    fun getNewCurrentWeather(currentWeatherEntity: CurrentWeatherEntity){
        currentWeatherDao.deleteExpiredData()
        currentWeatherDao.getNewCurrentWeather(currentWeatherEntity)
    }
}