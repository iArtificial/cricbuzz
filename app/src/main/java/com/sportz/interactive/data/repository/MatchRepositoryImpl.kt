package com.sportz.interactive.data.repository

import androidx.lifecycle.LiveData
import com.sportz.interactive.data.db.dao.MatchDao
import com.sportz.interactive.data.network.MatchDataSource
import com.sportz.interactive.data.network.response.Match
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MatchRepositoryImpl(
    private val matchDao: MatchDao,
    private val matchDataSource: MatchDataSource
) : MatchRepository {

    init {
        matchDataSource.downloadedMatch.observeForever { cricketFeed ->
            persistFetchedMatch(cricketFeed)
        }
    }


    override suspend fun getMatch(): LiveData<Match> {
        initCricketLiveFeedData()
        return withContext(Dispatchers.IO) {
            return@withContext matchDao.getMatchLiveFeed()
        }
    }

    private fun persistFetchedMatch(match: Match) {
        GlobalScope.launch(Dispatchers.IO) {
            matchDao.upsertMatchLiveFeed(match)
        }
    }

    private suspend fun initCricketLiveFeedData() {
        fetchLiveFeed()
    }

    private suspend fun fetchLiveFeed() {
        matchDataSource.fetchMatch()
    }
}