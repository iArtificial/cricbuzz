package com.sportz.interactive.data.db.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Player(
    @SerializedName("Position")
    val position: String,
    @SerializedName("Name_Full")
    val fullName: String,
    @SerializedName("Iskeeper")
    val isKeeper: Boolean?,
    @SerializedName("Iscaptain")
    val isCaptain: Boolean?
) : Serializable