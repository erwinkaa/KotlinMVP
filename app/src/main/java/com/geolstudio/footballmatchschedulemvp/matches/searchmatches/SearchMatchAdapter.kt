package com.geolstudio.aplikasifootballclub

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.model.match.DataMatches
import com.geolstudio.footballmatchschedulemvp.util.toLocaleTimeString
import com.geolstudio.footballmatchschedulemvp.util.toSimpleDateString
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.inflater_match.*
import kotlinx.android.synthetic.main.inflater_match.view.*

class SearchMatchAdapter(
        private val context: Context?,
        private val data: MutableList<DataMatches>
) :
    RecyclerView.Adapter<SearchMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.inflater_match, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position])
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(data: DataMatches) {
            itemView.tv_event_date.text = data.date_event.toSimpleDateString()
            var time = ""
            if (data.time_event != null) {
                time = data.time_event
            }
            itemView.tv_event_time.text = time
            itemView.tv_team1.text = data.team1_name
            itemView.tv_team2.text = data.team2_name
            itemView.tv_score_team1.text = data.team1_score
            itemView.tv_score_team2.text = data.team2_score
        }
    }
}