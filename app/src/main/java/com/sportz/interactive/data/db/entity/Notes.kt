package com.sportz.interactive.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.sportz.interactive.data.db.entity.typeconvertor.StringConverter

data class Notes(
    @TypeConverters(StringConverter::class)
    @ColumnInfo(name = "notes_innings_one")
    @SerializedName("1")
    val notesInningOne: List<String>,
    @TypeConverters(StringConverter::class)
    @ColumnInfo(name = "notes_innings_two")
    @SerializedName("2")
    val notesInningTwo: List<String>
)