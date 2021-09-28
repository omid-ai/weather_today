package com.example.weathertoday.model.db.entity.current

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current")
data class CurrentWeatherEntity(
    val dt: Int?,
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val cityName:String?,
    val country:String?,
    val weather_main:String?,
    val weather_icon:String?,
    val weather_description:String?,
    val cloudiness_percent:Int?,
    val feels_like: Double?,
    val humidity: Int?,
    val windSpeed:Double?,
    val temp:Double?,
    val temp_max:Double?,
    val temp_min: Double?
)