package com.example.weathertoday.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weathertoday.model.db.entity.forecast.WeatherForecastEntity

@Dao
interface WeatherForecastDao {

    @Query("SELECT * FROM forecast")
    fun getWeatherForecastDataFromDao():LiveData<WeatherForecastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewDataToDb(weatherForecastEntity: WeatherForecastEntity)

    @Query("DELETE FROM forecast")
    fun deleteExpiredData()
}