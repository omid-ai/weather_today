package com.example.weathertoday.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weathertoday.model.db.entity.current.CurrentWeatherEntity

@Dao
interface CurrentWeatherDao {

    @Query("SELECT * FROM current")
    fun getCurrentWeatherFromDb():LiveData<CurrentWeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun getNewCurrentWeather(currentWeatherEntity: CurrentWeatherEntity)

    @Query("DELETE FROM current")
    fun deleteExpiredData()
}