package com.sportz.interactive.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "teams")
data class Team(
    @SerializedName("Name_Full")
    val nameFull: String,
    @SerializedName("Name_Short")
    val nameShort: String,
    @SerializedName("Players")
    val players: Map<String, Player>
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}