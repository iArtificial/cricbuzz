package com.sportz.interactive.data.network

import androidx.lifecycle.LiveData
import com.sportz.interactive.data.network.response.Match

interface MatchDataSource {

    val downloadedMatch: LiveData<Match>

    suspend fun fetchMatch()
}