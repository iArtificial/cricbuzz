package com.sportz.interactive.data.db.entity.typeconvertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sportz.interactive.data.db.entity.Batsmen
import java.lang.reflect.Type

object BatsmenConverter {
    @TypeConverter
    fun fromString(value: String?): List<Batsmen> {
        val listType: Type = object : TypeToken<List<Batsmen>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<Batsmen?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}