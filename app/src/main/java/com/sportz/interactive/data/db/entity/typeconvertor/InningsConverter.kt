package com.sportz.interactive.data.db.entity.typeconvertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sportz.interactive.data.db.entity.Inning
import java.lang.reflect.Type

object InningsConverter {
    @TypeConverter
    fun fromString(value: String?): List<Inning> {
        val listType: Type = object : TypeToken<List<Inning?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<Inning?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}