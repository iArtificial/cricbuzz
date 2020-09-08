package com.sportz.interactive.data.db.entity


import com.google.gson.annotations.SerializedName

data class Venue(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String
)