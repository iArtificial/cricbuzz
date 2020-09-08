package com.sportz.interactive.data.db.entity


import com.google.gson.annotations.SerializedName

data class Teams(
    @SerializedName("team")
    val team: Map<String, Team>
)