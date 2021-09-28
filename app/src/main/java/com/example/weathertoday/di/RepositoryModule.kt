package com.example.weathertoday.di

import com.example.weathertoday.model.dataSource.current.CurrentWeatherLocalDataSource
import com.example.weathertoday.model.dataSource.current.CurrentWeatherRemoteDataSource
import com.example.weathertoday.model.dataSource.forecast.WeatherForecastLocalDataSource
import com.example.weathertoday.model.dataSource.forecast.WeatherForecastRemoteDataSource
import com.example.weathertoday.model.db.dao.CurrentWeatherDao
import com.example.weathertoday.model.db.dao.WeatherForecastDao
import com.example.weathertoday.model.repository.CurrentWeatherRepository
import com.example.weathertoday.model.repository.WeatherForecastRepository
import com.example.weathertoday.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCurrentWeatherRepository(
        apiService: ApiService,
        currentWeatherDao: CurrentWeatherDao
    ): CurrentWeatherRepository {
        return CurrentWeatherRepository(
            CurrentWeatherRemoteDataSource(apiService),
            CurrentWeatherLocalDataSource(currentWeatherDao)
        )
    }

    @Provides
    @Singleton
    fun provideWeatherForecastRepository(
        apiService: ApiService,
        weatherForecastDao: WeatherForecastDao
    ): WeatherForecastRepository {
        return WeatherForecastRepository(
            WeatherForecastRemoteDataSource(apiService),
            WeatherForecastLocalDataSource(weatherForecastDao)
        )
    }
}