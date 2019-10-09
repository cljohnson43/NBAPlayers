package com.example.nbaplayers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaplayers.R
import com.example.nbaplayers.models.Player

class PlayersAdapter(val playerList: List<Player>): RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_list_item, parent, false)
        return PlayersViewHolder(view)
    }

    override fun getItemCount(): Int = playerList.size

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        playerList.get(position).let {
            holder.tvFirstName.text = it.firstName
            holder.tvLastName.text = it.lastName
        }
    }

    class PlayersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvFirstName: TextView = itemView.findViewById(R.id.tv_first_name)
        val tvLastName: TextView = itemView.findViewById(R.id.tv_last_name)
    }
}