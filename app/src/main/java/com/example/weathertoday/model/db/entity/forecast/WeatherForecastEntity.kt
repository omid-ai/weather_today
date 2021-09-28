package com.example.weathertoday.model.db.entity.forecast

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast")
data class WeatherForecastEntity(
    @PrimaryKey(autoGenerate = false)
    val id:Int?,
    val city:String?,
    val country:String?,
    val timeZone:Int?,
    val sunrise: Int?,
    val sunset: Int?,
    val detailList: DetailList?
)
