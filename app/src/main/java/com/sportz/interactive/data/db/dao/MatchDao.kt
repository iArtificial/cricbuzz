package com.sportz.interactive.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sportz.interactive.data.network.response.Match

@Dao
interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertMatchLiveFeed(cricketLiveFeed: Match)

    @Query("Select * from `match`")
    fun getMatchLiveFeed(): LiveData<Match>
}