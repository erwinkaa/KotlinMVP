package com.geolstudio.footballmatchschedulemvp.teamdetail.players


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.api.ApiRepository
import com.geolstudio.footballmatchschedulemvp.model.players.DataPlayers
import com.geolstudio.footballmatchschedulemvp.playerdetail.PlayerDetailActivity
import com.geolstudio.footballmatchschedulemvp.util.invisible
import com.geolstudio.footballmatchschedulemvp.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.startActivity

class PlayersFragment : Fragment(), PlayersView {

    private val key = "ID"
    private var datas = mutableListOf<DataPlayers>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: PlayersAdapter
    private lateinit var presenter: PlayersPresenter

    fun newInstance(data: String): PlayersFragment {
        val args = Bundle()
        args.putSerializable(key, data)
        val fragment = PlayersFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_players, container, false)

        val idTeam = arguments?.getSerializable(key) as String

        progressBar = rootView.findViewById(R.id.progress_players)
        recyclerView = rootView.findViewById(R.id.recycler_players)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = PlayersAdapter(context, datas) { startActivity<PlayerDetailActivity>("data" to it) }
        recyclerView.adapter = adapter

        val gson = Gson()
        val apiRepository = ApiRepository()
        presenter = PlayersPresenter(this, gson, apiRepository)

        presenter.getPlayersTeam(idTeam)

        return rootView
    }

    override fun showLoading() {
        progressBar.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        recyclerView.visible()
    }

    override fun showPlayersList(players: List<DataPlayers>) {
        datas.clear()
        datas.addAll(players)
        adapter.notifyDataSetChanged()
    }

}
