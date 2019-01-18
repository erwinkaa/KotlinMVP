package com.geolstudio.footballmatchschedulemvp.teams

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.model.teams.DataTeams
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.inflater_team.view.*

class TeamsAdapter(
        private val context: Context?,
        private val data: MutableList<DataTeams>,
        private val onClickListener: (DataTeams) -> Unit
) :
    RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.inflater_team, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(context, data[position], onClickListener)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(context: Context?, data: DataTeams, onClickListener: (DataTeams) -> Unit) {
            Glide.with(context!!).load(data.team_badge).into(itemView.iv_team_badge)
            itemView.tv_team_name.text = data.team_name
            containerView.setOnClickListener { onClickListener(data) }
        }
    }
}