package com.sportz.interactive.data.db.entity


import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "match_details")
data class Matchdetail(
    @SerializedName("Equation")
    val equation: String,
    @Embedded(prefix = "match_")
    @SerializedName("Match")
    val match: Match,
    @Embedded(prefix = "officials_")
    @SerializedName("Officials")
    val officials: Officials,
    @SerializedName("Player_Match")
    val playerMatch: String,
    @SerializedName("Result")
    val result: String,
    @Embedded(prefix = "series_")
    @SerializedName("Series")
    val series: Series,
    @SerializedName("Status")
    val status: String,
    @SerializedName("Status_Id")
    val statusId: String,
    @SerializedName("Team_Away")
    val teamAway: String,
    @SerializedName("Team_Home")
    val teamHome: String,
    @SerializedName("Tosswonby")
    val tosswonby: String,
    @SerializedName("Venue")
    @Embedded(prefix = "venue_")
    val venue: Venue,
    @SerializedName("Weather")
    val weather: String,
    @SerializedName("Winmargin")
    val winmargin: String,
    @SerializedName("Winningteam")
    val winningteam: String
)