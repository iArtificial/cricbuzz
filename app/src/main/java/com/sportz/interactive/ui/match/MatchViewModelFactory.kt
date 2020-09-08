package com.sportz.interactive.ui.match

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sportz.interactive.data.repository.MatchRepository

class MatchViewModelFactory(
    private val matchRepository: MatchRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MatchViewModel(matchRepository) as T
    }
}