package com.example.weathertoday.di

import android.content.Context
import androidx.room.Room
import com.example.weathertoday.model.db.WeatherDb
import com.example.weathertoday.model.db.dao.CurrentWeatherDao
import com.example.weathertoday.model.db.dao.WeatherForecastDao
import com.example.weathertoday.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomDb(
        @ApplicationContext context: Context
    ): WeatherDb {
        return Room.databaseBuilder(context, WeatherDb::class.java, "app_db").build()
    }

    @Provides
    @Singleton
    fun provideCurrentWeatherDao(weatherDb: WeatherDb): CurrentWeatherDao {
        return weatherDb.getCurrentWeather()
    }

    @Provides
    @Singleton
    fun provideWeatherForecastDao(weatherDb: WeatherDb): WeatherForecastDao {
        return weatherDb.getWeatherForecast()
    }
}