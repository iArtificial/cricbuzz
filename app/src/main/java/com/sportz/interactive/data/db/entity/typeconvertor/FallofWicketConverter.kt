package com.sportz.interactive.data.db.entity.typeconvertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sportz.interactive.data.db.entity.FallofWicket
import java.lang.reflect.Type

object FallofWicketConverter {
    @TypeConverter
    fun fromString(value: String?): List<FallofWicket> {
        val listType: Type = object : TypeToken<List<FallofWicket?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<FallofWicket?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}