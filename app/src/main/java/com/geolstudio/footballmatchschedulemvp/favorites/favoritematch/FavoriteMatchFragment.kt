package com.geolstudio.footballmatchschedulemvp.favorites.favoritematch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.geolstudio.aplikasifootballclub.FavoriteMatchAdapter

import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.matchdetail.MatchDetailActivity
import com.geolstudio.footballmatchschedulemvp.model.match.DataMatches
import com.geolstudio.footballmatchschedulemvp.util.invisible
import com.geolstudio.footballmatchschedulemvp.util.visible
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FavoriteMatchFragment : Fragment(),
    FavoriteMatchView {

    private var datas = mutableListOf<DataMatches>()
    private lateinit var presenter: FavoriteMatchPresenter
    private lateinit var adapter: FavoriteMatchAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorite_match, container, false)

        swipeRefreshLayout = rootView.findViewById(R.id.refresh_favmatch)
        progressBar = rootView.findViewById(R.id.progress_favmatch)
        recyclerView = rootView.findViewById(R.id.recycler_favmatch)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = FavoriteMatchAdapter(context, datas) {
            startActivity<MatchDetailActivity>("data" to it)
        }
        recyclerView.adapter = adapter

        presenter = FavoriteMatchPresenter(this)
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

    override fun showFavoriteMatchList(data: List<DataMatches>) {
        swipeRefreshLayout.isRefreshing = false
        datas.clear()
        datas.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
