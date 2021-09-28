package com.example.weathertoday.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weathertoday.model.db.entity.current.CurrentWeatherEntity
import com.example.weathertoday.model.db.entity.forecast.WeatherForecastEntity
import com.example.weathertoday.model.repository.CurrentWeatherRepository
import com.example.weathertoday.model.repository.WeatherForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currentWeatherRepository: CurrentWeatherRepository,
    private val weatherForecastRepository: WeatherForecastRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getCurrentWeatherDataAndRefreshDb(cityName: String) {
        return currentWeatherRepository.getCurrentWeather(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {
                    Timber.i("done")
                }

                override fun onError(e: Throwable) {
                    Timber.e("error(current)-> $e")
                }

            })
    }

    fun getCurrentWeather(): LiveData<CurrentWeatherEntity> {
        return currentWeatherRepository.getCurrentWeatherFromDao()
    }

    fun getWeatherForecastDataAndRefreshDb(cityName: String) {
        weatherForecastRepository.getWeatherForecast(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {
                    Timber.i("done")
                }

                override fun onError(e: Throwable) {
                    Timber.e("error(forecast)-> $e")
                }

            })
    }

    fun getWeatherForecast(): LiveData<WeatherForecastEntity> {
        return weatherForecastRepository.getWeatherForecastFromDb()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}