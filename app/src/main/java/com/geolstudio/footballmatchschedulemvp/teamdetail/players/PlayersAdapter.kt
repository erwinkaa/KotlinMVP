package com.geolstudio.footballmatchschedulemvp.teamdetail.players

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.model.players.DataPlayers
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.inflater_player.view.*

class PlayersAdapter(
    private val context: Context?,
    private val data: MutableList<DataPlayers>,
    private val onClickListener: (DataPlayers) -> Unit
) :
    RecyclerView.Adapter<PlayersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.inflater_player, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(context, data[position], onClickListener)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(context: Context?, data: DataPlayers, onClickListener: (DataPlayers) -> Unit) {
            if (data.player_thumbnail != null) {
                Glide.with(context!!).load(data.player_thumbnail).into(itemView.iv_player_thumbnail)
            } else {
                Glide.with(context!!).load(R.drawable.notfound).into(itemView.iv_player_thumbnail)
            }
            itemView.tv_player_name.text = data.player_name
            itemView.tv_player_position.text = data.player_position
            containerView.setOnClickListener { onClickListener(data) }
        }
    }
}