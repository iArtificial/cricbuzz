package com.sportz.interactive.ui.match.info

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sportz.interactive.R
import com.sportz.interactive.data.db.entity.Player
import com.sportz.interactive.data.db.entity.Team
import com.sportz.interactive.internal.Constants.KEY_PLAYERS
import com.sportz.interactive.internal.Constants.KEY_SQUAD_TITLE
import com.sportz.interactive.ui.base.ScopedFragment
import com.sportz.interactive.ui.match.MatchViewModel
import com.sportz.interactive.ui.match.MatchViewModelFactory
import com.sportz.interactive.ui.match.squad.SquadActivity
import com.sportz.interactive.ui.match.team.TeamFragment
import kotlinx.android.synthetic.main.fragment_matchinfo.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MatchInfoFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: MatchViewModelFactory by instance()

    private lateinit var viewmodel: MatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_matchinfo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(MatchViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {
        val matchLiveFeed = viewmodel.match.await()
        group_loading.visibility = View.GONE
        ctl_root.visibility = View.VISIBLE

        matchLiveFeed.observe(viewLifecycleOwner, Observer { response ->

            val matchDetail = response.matchDetail

            val homeTeam = response.teams[matchDetail.teamHome]
            val awayTeam = response.teams[matchDetail.teamAway]

            val homeInnings =
                response.innings.find { inning -> inning.battingteam == matchDetail.teamHome }
            val awayInnings =
                response.innings.find { inning -> inning.battingteam == matchDetail.teamAway }

            textView_homeFullName.text = homeTeam?.nameFull
            textView_awayFullName.text = awayTeam?.nameFull
            textView_match.text = matchDetail.match.number
            textView_series.text = matchDetail.series.name
            textView_date.text = matchDetail.match.date
            textView_venue.text = matchDetail.venue.name

            val homeTeamPlayers = getTeamPlayers(homeTeam)
            val awayTeamPlayers = getTeamPlayers(awayTeam)

            ctl_homeSquad.setOnClickListener {
                launchSquadActivity(homeTeam, homeTeamPlayers)
            }

            ctl_awaySquad.setOnClickListener {
                launchSquadActivity(awayTeam, awayTeamPlayers)
            }

            val teamValues = response.teams.values
            val teams = arrayListOf<Team?>()
            teamValues.let { allTeams -> teams.addAll(allTeams) }

            val pageAdapter = TeamsPagerAdapter(childFragmentManager)
            for (i in 0 until teams.size) {
                teams[i]?.let { team ->
                    val playerValues = team.players.values
                    val players = arrayListOf<Player?>()
                    playerValues.let { allPlayers -> players.addAll(allPlayers) }

                    pageAdapter.add(
                        TeamFragment.newInstance(i, players),
                        team.nameFull
                    )
                }
            }
            viewPager.adapter = pageAdapter
            tabLayout.setupWithViewPager(viewPager)
        })
    }

    private fun launchSquadActivity(
        team: Team?,
        players: ArrayList<Player>
    ) {
        val intent = Intent(activity, SquadActivity::class.java)
        intent.putExtra(KEY_SQUAD_TITLE, team?.nameFull)
        intent.putExtra(KEY_PLAYERS, players)
        startActivity(intent)
    }

    private fun getTeamPlayers(team: Team?): ArrayList<Player> {
        val playerValues = team?.players?.values
        val teamPlayers = arrayListOf<Player>()
        playerValues?.let { allPlayers -> teamPlayers.addAll(allPlayers) }
        return teamPlayers
    }

    private fun updateTitle(title: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }
}