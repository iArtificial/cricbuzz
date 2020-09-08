package com.sportz.interactive.ui.match

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.sportz.interactive.R
import kotlinx.android.synthetic.main.activity_match.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MatchActivity : AppCompatActivity(), KodeinAware {

    private lateinit var navController: NavController

    override val kodein by closestKodein()

    private val viewModelFactory: MatchViewModelFactory by instance()
    private lateinit var viewmodel: MatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)
        setSupportActionBar(findViewById(R.id.toolbar))

        navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )

        bottom_nav.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this, navController)
        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(MatchViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = GlobalScope.launch(Dispatchers.Main) {
        val liveFeed = viewmodel.match.await()
        liveFeed.observe(this@MatchActivity, { response ->

            // Handle if response equals to null case for the first time.

            val matchDetail = response.matchDetail

            val homeTeam = response.teams[matchDetail.teamHome]
            val awayTeam = response.teams[matchDetail.teamAway]

            val homeInnings =
                response.innings.find { inning -> inning.battingteam == matchDetail.teamHome }
            val awayInnings =
                response.innings.find { inning -> inning.battingteam == matchDetail.teamAway }

            textView_result.text = matchDetail.result
            textView_homeShortName.text = homeTeam?.nameShort
            textView_awayShortName.text = awayTeam?.nameShort
            textView_homeRuns.text = homeInnings?.total
            textView_awayRuns.text = awayInnings?.total
            textView_awayOvers.text = awayInnings?.overs
            textView_awayOvers.text = awayInnings?.overs

            val x = 1
//            val teamValues = response.teams.values
//            val teams = arrayListOf<Team?>()
//            teamValues.let { allTeams -> teams.addAll(allTeams) }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}