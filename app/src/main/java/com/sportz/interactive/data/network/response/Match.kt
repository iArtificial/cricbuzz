package com.sportz.interactive.data.network.response


import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.sportz.interactive.data.db.entity.Inning
import com.sportz.interactive.data.db.entity.Matchdetail
import com.sportz.interactive.data.db.entity.Notes
import com.sportz.interactive.data.db.entity.Team
import com.sportz.interactive.data.db.entity.typeconvertor.InningsConverter
import com.sportz.interactive.data.db.entity.typeconvertor.TeamsMapTypeConverter

@Entity(tableName = "match")
data class Match(
    @TypeConverters(InningsConverter::class)
    @ColumnInfo(name = "innings")
    @SerializedName("Innings")
    val innings: List<Inning>,
    @Embedded(prefix = "notes_")
    @SerializedName("Notes")
    val notes: Notes,
    @Embedded(prefix = "match_details_")
    @SerializedName("Matchdetail")
    val matchDetail: Matchdetail,
    @TypeConverters(TeamsMapTypeConverter::class)
    @ColumnInfo(name = "teams")
    @SerializedName("Teams")
    val teams: Map<String, Team>
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}