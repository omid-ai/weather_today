package com.example.weathertoday.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weathertoday.model.db.dao.CurrentWeatherDao
import com.example.weathertoday.model.db.dao.WeatherForecastDao
import com.example.weathertoday.model.db.entity.current.CurrentWeatherEntity
import com.example.weathertoday.model.db.entity.forecast.WeatherForecastEntity
import com.example.weathertoday.util.TypeConverter

@Database(
    entities = [CurrentWeatherEntity::class, WeatherForecastEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(TypeConverter::class)
abstract class WeatherDb:RoomDatabase() {
    abstract fun getCurrentWeather(): CurrentWeatherDao
    abstract fun getWeatherForecast(): WeatherForecastDao
}