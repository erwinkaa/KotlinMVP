package com.geolstudio.footballmatchschedulemvp.favorites.favoriteteams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.model.teams.DataTeams
import com.geolstudio.footballmatchschedulemvp.teamdetail.TeamDetailActivity
import com.geolstudio.footballmatchschedulemvp.teams.TeamsAdapter
import com.geolstudio.footballmatchschedulemvp.util.invisible
import com.geolstudio.footballmatchschedulemvp.util.visible
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FavoriteTeamFragment : Fragment(), FavoriteTeamView {

    private var datas = mutableListOf<DataTeams>()
    private lateinit var presenter: FavoriteTeamPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorite_team, container, false)

        swipeRefreshLayout = rootView.findViewById(R.id.refresh_favteam)
        progressBar = rootView.findViewById(R.id.progress_favteam)
        recyclerView = rootView.findViewById(R.id.recycler_favteam)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = TeamsAdapter(context, datas) { startActivity<TeamDetailActivity>("data" to it) }
        recyclerView.adapter = adapter

        presenter = FavoriteTeamPresenter(this)
        presenter.getFavoriteMatch(context)

        swipeRefreshLayout.onRefresh {
            presenter.getFavoriteMatch(context)
        }

        return rootView
    }

    override fun showLoading() {
        if(!swipeRefreshLayout.isRefreshing) {
            progressBar.visible()
        }
        recyclerView.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        recyclerView.visible()
    }

    override fun showFavoriteTeamList(team: List<DataTeams>) {
        swipeRefreshLayout.isRefreshing = false
        datas.clear()
        datas.addAll(team)
        adapter.notifyDataSetChanged()
    }
}
