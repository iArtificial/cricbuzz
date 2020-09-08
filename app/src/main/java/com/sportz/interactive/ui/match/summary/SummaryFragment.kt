package com.sportz.interactive.ui.match.summary

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sportz.interactive.R
import com.sportz.interactive.data.db.entity.Inning
import com.sportz.interactive.data.db.entity.PlayerBattingSummary
import com.sportz.interactive.data.db.entity.Team
import com.sportz.interactive.ui.base.ScopedFragment
import com.sportz.interactive.ui.match.MatchViewModel
import com.sportz.interactive.ui.match.MatchViewModelFactory
import kotlinx.android.synthetic.main.summary_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SummaryFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: MatchViewModelFactory by instance()
    private lateinit var viewmodel: MatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.summary_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arrowBtn.setOnClickListener {
            if (expandableView.visibility == View.GONE) {
                expandableView.visibility = View.VISIBLE
                arrowBtn.setBackgroundResource(R.drawable.ic_arrow_down)
            } else {
                expandableView.visibility = View.GONE
                arrowBtn.setBackgroundResource(R.drawable.ic_arrow_down)
            }
        }

        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(MatchViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {
        val matchLiveFeed = viewmodel.match.await()

        matchLiveFeed.observe(viewLifecycleOwner, Observer { response ->

            val matchDetail = response.matchDetail

            val homeTeam = response.teams[matchDetail.teamHome]
            val awayTeam = response.teams[matchDetail.teamAway]

            textView_homeFullName.text = homeTeam?.nameFull
            textView_awayFullName.text = awayTeam?.nameFull

            val homeInnings =
                response.innings.find { inning -> inning.battingteam == matchDetail.teamHome }
            val awayInnings =
                response.innings.find { inning -> inning.battingteam == matchDetail.teamAway }

            val playersBattingSummaryHome = getPlayerBattingSummary(homeInnings, homeTeam)
            val playersBattingSummaryAway = getPlayerBattingSummary(homeInnings, homeTeam)

            val playersBattingSummaryAdapter = PlayersBattingSummaryAdapter(requireContext())
            recyclerView.apply {
                adapter = playersBattingSummaryAdapter
                layoutManager = LinearLayoutManager(activity)
                addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
            }
            playersBattingSummaryAdapter.setPlayerSummary(playersBattingSummaryHome)
        })
    }

    private fun getPlayerBattingSummary(
        innings: Inning?,
        team: Team?
    ): ArrayList<PlayerBattingSummary> {
        val playersBattingSummary = arrayListOf<PlayerBattingSummary>()

        innings?.batsmen?.forEach { man ->
            man?.let {
                val batsmanId = man.batsman
                val playerBattingSummary = PlayerBattingSummary(
                    team?.players?.get(batsmanId)?.fullName,
                    man.runs,
                    man.balls,
                    man.howout
                )
                playersBattingSummary.add(playerBattingSummary)
            }
        }
        return playersBattingSummary
    }


}