package com.sportz.interactive.data.repository

import androidx.lifecycle.LiveData
import com.sportz.interactive.data.network.response.Match

interface MatchRepository {
    suspend fun getMatch(): LiveData<Match>
}