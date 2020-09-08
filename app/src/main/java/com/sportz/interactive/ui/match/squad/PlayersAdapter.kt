package com.sportz.interactive.ui.match.squad

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sportz.interactive.R
import com.sportz.interactive.data.db.entity.Player

class PlayersAdapter internal constructor(
    adapterContext: Context
) : RecyclerView.Adapter<PlayersAdapter.DeviceViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(adapterContext)
    private var players = arrayListOf<Player?>()

    class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: TextView = itemView.findViewById(R.id.textView_player_name)
        val playerStatus: ImageView = itemView.findViewById(R.id.imageView_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val itemView = inflater.inflate(R.layout.item_player, parent, false)
        return DeviceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val player = players[position]
        holder.playerName.text = player?.fullName
        if (player?.isCaptain == true)
            holder.playerStatus.visibility = View.VISIBLE

    }

    internal fun setPlayers(players: ArrayList<Player?>) {
        this.players = players
        notifyDataSetChanged()
    }

    override fun getItemCount() = players.size
}