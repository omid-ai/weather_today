package com.example.weathertoday.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertoday.R
import com.example.weathertoday.databinding.ActivityMainBinding
import com.example.weathertoday.model.dataClass.forecast.Detail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import timber.log.Timber


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()
    val hourlyForecastAdapter = HourlyForecastAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.weatherForecastRv.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        binding.searchBtn.setOnClickListener {
            binding.searchBtn.visibility = View.GONE
            binding.searchCityEtLayout.visibility = View.VISIBLE
        }
        binding.searchCityEtLayout.setEndIconOnClickListener {
            if (!binding.searchCityEt.text.isNullOrEmpty()) {
                viewModel.getCurrentWeatherDataAndRefreshDb(binding.searchCityEt.text.toString())
                viewModel.getWeatherForecastDataAndRefreshDb(binding.searchCityEt.text.toString())
                binding.searchCityEtLayout.visibility = View.GONE
                binding.searchBtn.visibility = View.VISIBLE
            } else {
                binding.searchCityEt.hint = getString(R.string.notNull)
            }
        }

        viewModel.getCurrentWeather().observe(this) {
            Timber.i("Current weather data-> $it")
            if (it != null) {
                binding.cityNameTv.text = it.cityName
                binding.descriptionTv.text = it.weather_description
                binding.temperatureDegreeTv.text = it.temp?.let { temp -> getTempString(temp) }
                binding.windTv.text= it.windSpeed.toString()
                binding.feelsLikeTv.text=it.feels_like.toString()
                binding.cloudinessTv.text=it.cloudiness_percent.toString()
                binding.humidityTv.text=it.humidity.toString()
                binding.countryNameTv.text = it.country
                when (it.weather_icon) {
                    "01d" -> binding.weatherIv.setImageResource(R.drawable.icons8_sun_64)
                    "01n" -> binding.weatherIv.setImageResource(R.drawable.icons8_moon_58)
                    "02n" -> binding.weatherIv.setImageResource(R.drawable.icons8_moon_clouds_64)
                    "02d" -> binding.weatherIv.setImageResource(R.drawable.icons8_sun_cloud_64)
                    "03d" -> binding.weatherIv.setImageResource(R.drawable.icons8_cloud_60)
                    "03n" -> binding.weatherIv.setImageResource(R.drawable.icons8_cloud_60)
                    "04n" -> binding.weatherIv.setImageResource(R.drawable.icons8_cloud_60)
                    "04d" -> binding.weatherIv.setImageResource(R.drawable.icons8_cloud_60)
                    "09d" -> binding.weatherIv.setImageResource(R.drawable.icons8_rain_48)
                    "09n" -> binding.weatherIv.setImageResource(R.drawable.icons8_rain_48)
                    "010d" -> binding.weatherIv.setImageResource(R.drawable.icons8_rain_48)
                    "010n" -> binding.weatherIv.setImageResource(R.drawable.icons8_rain_48)
                    "011n" -> binding.weatherIv.setImageResource(R.drawable.icons8_thunderstorm_60)
                    "011d" -> binding.weatherIv.setImageResource(R.drawable.icons8_thunderstorm_60)
                    "013d" -> binding.weatherIv.setImageResource(R.drawable.snow)
                    "013n" -> binding.weatherIv.setImageResource(R.drawable.snow)
                    "050n" -> binding.weatherIv.setImageResource(R.drawable.mist)
                    "050d" -> binding.weatherIv.setImageResource(R.drawable.mist)
                }
            }
        }

        viewModel.getWeatherForecast().observe(this) {
            Timber.i("weather forecast data-> $it")
            if (it != null) {
                hourlyForecastAdapter.detailWeatherForecastResponse =
                    it.detailList?.detailList as MutableList<Detail>
                binding.weatherForecastRv.adapter = hourlyForecastAdapter
                it.detailList.detailList.forEach { detail ->

                }
            }
        }
//        val date=LocalDate()
        val currentMoment: Instant = Clock.System.now()
        binding.dateTv.text=currentMoment.toString()
    }

    private fun getTempString(temp:Double): String {
        return temp.toString().substringBefore(".") + "°c"
    }
}