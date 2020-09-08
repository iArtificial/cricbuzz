package com.sportz.interactive.data.db.entity.typeconvertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sportz.interactive.data.db.entity.Bowler
import java.lang.reflect.Type

object BowlerConverter {
    @TypeConverter
    fun fromString(value: String?): List<Bowler> {
        val listType: Type = object : TypeToken<List<Bowler?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<Bowler?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}