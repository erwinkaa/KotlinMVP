package com.geolstudio.footballmatchschedulemvp.matches.childmatches

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.model.match.DataMatches
import com.geolstudio.footballmatchschedulemvp.util.invisible
import com.geolstudio.footballmatchschedulemvp.util.toLocaleTimeString
import com.geolstudio.footballmatchschedulemvp.util.toSimpleLocaleDateString
import com.geolstudio.footballmatchschedulemvp.util.visible
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.inflater_match.view.*

class MatchAdapter(
    private val context: Context?,
    private val data: MutableList<DataMatches>,
    private val type: String,
    private val onClickListener: (DataMatches) -> Unit,
    private val add_to_calendar: (DataMatches) -> Unit
) :
    RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.inflater_match, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], type, onClickListener, add_to_calendar)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindItem(
            data: DataMatches,
            type: String,
            onClickListener: (DataMatches) -> Unit,
            add_to_calendar: (DataMatches) -> Unit
        ) {
            val affectedDatebyTime = (data.date_event + " " + data.time_event).toSimpleLocaleDateString()
            itemView.tv_event_date.text = affectedDatebyTime
            itemView.tv_event_time.text = data.time_event.toLocaleTimeString()
            itemView.tv_team1.text = data.team1_name
            itemView.tv_team2.text = data.team2_name
            itemView.tv_score_team1.text = data.team1_score
            itemView.tv_score_team2.text = data.team2_score
            containerView.setOnClickListener { onClickListener(data) }
            if (type.equals("next")) itemView.add_to_calendar.visible() else itemView.add_to_calendar.invisible()
            itemView.add_to_calendar.setOnClickListener { add_to_calendar(data) }
        }
    }
}