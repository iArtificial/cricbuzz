package com.sportz.interactive.data.db.entity


import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.sportz.interactive.data.db.entity.typeconvertor.BatsmenConverter
import com.sportz.interactive.data.db.entity.typeconvertor.BowlerConverter
import com.sportz.interactive.data.db.entity.typeconvertor.FallofWicketConverter

@Entity(tableName = "innings")
data class Inning(
    @SerializedName("AllottedOvers")
    val allottedOvers: String,

    @TypeConverters(BatsmenConverter::class)
    @ColumnInfo(name = "batsmen")
    @SerializedName("Batsmen")
    val batsmen: List<Batsmen?>?,
    @SerializedName("Battingteam")
    val battingteam: String?,

    @TypeConverters(BowlerConverter::class)
    @ColumnInfo(name = "bowlers")
    @SerializedName("Bowlers")
    val bowlers: List<Bowler>,
    @SerializedName("Byes")
    val byes: String?,

    @TypeConverters(FallofWicketConverter::class)
    @ColumnInfo(name = "fall_of_wickets")
    @SerializedName("FallofWickets")
    val fallofWickets: List<FallofWicket>,
    @SerializedName("Legbyes")
    val legbyes: String?,
    @SerializedName("Noballs")
    val noballs: String?,
    @SerializedName("Number")
    val number: String?,
    @SerializedName("Overs")
    val overs: String?,
    @Embedded(prefix = "partnership_current_")
    @SerializedName("Partnership_Current")
    val partnershipCurrent: PartnershipCurrent?,
    @SerializedName("Penalty")
    val penalty: String?,
    @Embedded(prefix = "power_play_")
    @SerializedName("PowerPlay")
    val powerPlay: PowerPlay?,
    @SerializedName("Runrate")
    val runrate: String?,
    @SerializedName("Target")
    val target: String?,
    @SerializedName("Total")
    val total: String?,
    @SerializedName("Wickets")
    val wickets: String?,
    @SerializedName("Wides")
    val wides: String?
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}