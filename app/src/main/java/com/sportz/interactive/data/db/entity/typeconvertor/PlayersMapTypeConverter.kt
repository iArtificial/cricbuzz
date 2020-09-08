package com.sportz.interactive.data.db.entity.typeconvertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sportz.interactive.data.db.entity.Player

object PlayersMapTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Map<String, Player> {
        val mapType = object : TypeToken<Map<String?, Player>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromStringMap(map: Map<String?, Player>): String {
        val gson = Gson()
        return gson.toJson(map)
    }
}