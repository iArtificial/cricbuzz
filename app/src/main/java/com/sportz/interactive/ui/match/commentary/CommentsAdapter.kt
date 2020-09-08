package com.sportz.interactive.ui.match.commentary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sportz.interactive.R

class CommentsAdapter internal constructor(
    adapterContext: Context
) : RecyclerView.Adapter<CommentsAdapter.DeviceViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(adapterContext)
    private var comments = arrayListOf<String>()

    class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val note: TextView = itemView.findViewById(R.id.textView_note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val itemView = inflater.inflate(R.layout.item_note, parent, false)
        return DeviceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val note = comments[position]
        holder.note.text = note
    }

    internal fun setComments(comments: ArrayList<String>) {
        this.comments.clear()
        this.comments.addAll(comments)
        notifyDataSetChanged()
    }

    override fun getItemCount() = comments.size
}