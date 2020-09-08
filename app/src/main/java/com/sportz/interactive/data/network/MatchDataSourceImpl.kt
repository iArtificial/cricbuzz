package com.sportz.interactive.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sportz.interactive.data.YahooCricketApiService
import com.sportz.interactive.data.network.response.Match
import com.sportz.interactive.internal.NoConnectivityException

class MatchDataSourceImpl(
    private val yahooCricketApiService: YahooCricketApiService
) : MatchDataSource {
    private val _downloadedMatch = MutableLiveData<Match>()

    override val downloadedMatch: LiveData<Match>
        get() = _downloadedMatch

    override suspend fun fetchMatch() {
        try {
            val fetchedMatch = yahooCricketApiService.getMatchAsync().await()
            _downloadedMatch.postValue(fetchedMatch)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.")
        }
    }
}