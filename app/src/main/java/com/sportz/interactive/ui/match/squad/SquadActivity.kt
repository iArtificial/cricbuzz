package com.sportz.interactive.ui.match.squad

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sportz.interactive.R
import com.sportz.interactive.data.db.entity.Player
import com.sportz.interactive.internal.Constants.KEY_PLAYERS
import com.sportz.interactive.internal.Constants.KEY_SQUAD_TITLE
import kotlinx.android.synthetic.main.team_fragment.*

class SquadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_squad)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val teamFullName = intent.getStringExtra(KEY_SQUAD_TITLE)
        val players = intent.getSerializableExtra(KEY_PLAYERS) as ArrayList<Player?>

        supportActionBar?.title = "$teamFullName Playing XI"

        val playersAdapter = PlayersAdapter(this)

        recyclerView.apply {
            adapter = playersAdapter
            layoutManager = LinearLayoutManager(this@SquadActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@SquadActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
        playersAdapter.setPlayers(players)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}