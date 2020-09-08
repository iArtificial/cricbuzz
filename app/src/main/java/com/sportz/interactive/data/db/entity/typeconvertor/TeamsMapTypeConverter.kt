package com.sportz.interactive.data.db.entity.typeconvertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sportz.interactive.data.db.entity.Team

object TeamsMapTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Map<String, Team> {
        val mapType = object : TypeToken<Map<String?, Team?>?>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromStringMap(map: Map<String?, Team?>?): String {
        val gson = Gson()
        return gson.toJson(map)
    }
}