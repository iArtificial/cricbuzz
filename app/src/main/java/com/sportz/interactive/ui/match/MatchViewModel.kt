package com.sportz.interactive.ui.match

import androidx.lifecycle.ViewModel
import com.sportz.interactive.data.repository.MatchRepository
import com.sportz.interactive.internal.lazyDeferred

class MatchViewModel(private val matchRepository: MatchRepository) : ViewModel() {
    val match by lazyDeferred { matchRepository.getMatch() }
}