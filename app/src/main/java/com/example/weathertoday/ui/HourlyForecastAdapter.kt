package com.example.weathertoday.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertoday.R
import com.example.weathertoday.databinding.ItemHourlyForecastBinding
import com.example.weathertoday.model.dataClass.forecast.Detail

class HourlyForecastAdapter : RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder>() {

    lateinit var binding: ItemHourlyForecastBinding
    var detailWeatherForecastResponse: MutableList<Detail>? = null

    inner class ViewHolder(binding: ItemHourlyForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(detail: Detail) {
            binding.weatherItemDegreeTv.text = detail.main?.temp.toString()
            binding.weatherItemHourTv.text = detail.getHourOfDay()
            detail.weather?.forEach {
                when (it.icon) {
                    "01d" -> binding.weatherItemIv.setImageResource(R.drawable.day_clear_sky)
                    "01n" -> binding.weatherItemIv.setImageResource(R.drawable.night_clear_sky)
                    "02n" -> binding.weatherItemIv.setImageResource(R.drawable.moon_behind_clouds)
                    "02d" -> binding.weatherItemIv.setImageResource(R.drawable.sun_behind_clouds)
                    "03d" -> binding.weatherItemIv.setImageResource(R.drawable.ic_baseline_cloud_24)
                    "03n" -> binding.weatherItemIv.setImageResource(R.drawable.ic_baseline_cloud_24)
                    "04n" -> binding.weatherItemIv.setImageResource(R.drawable.ic_baseline_cloud_24)
                    "04d" -> binding.weatherItemIv.setImageResource(R.drawable.ic_baseline_cloud_24)
                    "09d" -> binding.weatherItemIv.setImageResource(R.drawable.rainy)
                    "09n" -> binding.weatherItemIv.setImageResource(R.drawable.rainy)
                    "010d" -> binding.weatherItemIv.setImageResource(R.drawable.rainy)
                    "010n" -> binding.weatherItemIv.setImageResource(R.drawable.rainy)
                    "011n" -> binding.weatherItemIv.setImageResource(R.drawable.thunder)
                    "011d" -> binding.weatherItemIv.setImageResource(R.drawable.thunder)
                    "013d" -> binding.weatherItemIv.setImageResource(R.drawable.snowy)
                    "013n" -> binding.weatherItemIv.setImageResource(R.drawable.snowy)
                    "050n" -> binding.weatherItemIv.setImageResource(R.drawable.misty)
                    "050d" -> binding.weatherItemIv.setImageResource(R.drawable.misty)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ItemHourlyForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(detailWeatherForecastResponse!![position])
    }

    override fun getItemCount(): Int {
        if (detailWeatherForecastResponse!!.size > 10) {
            return 11
        } else
            return detailWeatherForecastResponse!!.size
    }
}