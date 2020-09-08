package com.sportz.interactive.ui.match.summary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sportz.interactive.R
import com.sportz.interactive.data.db.entity.PlayerBattingSummary

class PlayersBattingSummaryAdapter internal constructor(
    adapterContext: Context
) : RecyclerView.Adapter<PlayersBattingSummaryAdapter.DeviceViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(adapterContext)
    private var playerSummary = arrayListOf<PlayerBattingSummary>()

    class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: TextView = itemView.findViewById(R.id.textView_player_name)
        val playerRuns: TextView = itemView.findViewById(R.id.textView_runs)
        val playerBalls: TextView = itemView.findViewById(R.id.textView_balls)
        val playerHowOut: TextView = itemView.findViewById(R.id.textView_howOut)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val itemView = inflater.inflate(R.layout.item_player_batting_summary, parent, false)
        return DeviceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val playerSummary = playerSummary[position]
        holder.playerName.text = playerSummary.name
        holder.playerRuns.text = playerSummary.runs
        holder.playerBalls.text = playerSummary.balls
        holder.playerHowOut.text = playerSummary.howOut
    }

    internal fun setPlayerSummary(playerSummary: ArrayList<PlayerBattingSummary>) {
        this.playerSummary.clear()
        this.playerSummary.addAll(playerSummary)
        notifyDataSetChanged()
    }

    override fun getItemCount() = playerSummary.size
}