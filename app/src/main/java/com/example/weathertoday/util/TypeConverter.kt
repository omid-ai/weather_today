package com.example.weathertoday.util

import androidx.room.TypeConverter
import com.example.weathertoday.model.db.entity.forecast.DetailList
import com.google.gson.Gson

class TypeConverter {

    @TypeConverter
    fun toDetailList(value: String): DetailList {
        return Gson().fromJson(value, DetailList::class.java)
    }

    @TypeConverter
    fun fromDetailList(value: DetailList): String {
        return Gson().toJson(value)
    }
}