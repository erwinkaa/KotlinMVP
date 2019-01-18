package com.geolstudio.footballmatchschedulemvp.teams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.Spinner

import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.R.id.m_search
import com.geolstudio.footballmatchschedulemvp.R.menu.menu_search_teams
import com.geolstudio.footballmatchschedulemvp.api.ApiRepository
import com.geolstudio.footballmatchschedulemvp.matches.childmatches.SpinnerLeaguesAdapter
import com.geolstudio.footballmatchschedulemvp.model.leagues.DataLeagues
import com.geolstudio.footballmatchschedulemvp.model.teams.DataTeams
import com.geolstudio.footballmatchschedulemvp.teamdetail.TeamDetailActivity
import com.geolstudio.footballmatchschedulemvp.util.invisible
import com.geolstudio.footballmatchschedulemvp.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.startActivity

class TeamsFragment : Fragment(), TeamsView {

    private var teams = mutableListOf<DataTeams>()
    private var leagues = mutableListOf<DataLeagues>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var toolbar: Toolbar
    private lateinit var adapterSpinner: SpinnerLeaguesAdapter
    private lateinit var adapterRecycler: TeamsAdapter
    private lateinit var presenter: TeamsPresenter
    private lateinit var leagueId: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_teams, container, false)

        toolbar = rootView.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.teams)
        setHasOptionsMenu(true)

        progressBar = rootView.findViewById(R.id.progress_teams)
        spinner = rootView.findViewById(R.id.spinner)
        recyclerView = rootView.findViewById(R.id.recycler_teams)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapterRecycler = TeamsAdapter(context, teams) { startActivity<TeamDetailActivity>("data" to it) }
        recyclerView.adapter = adapterRecycler

        val gson = Gson()
        val apiRepository = ApiRepository()
        presenter = TeamsPresenter(this, gson, apiRepository)

        adapterSpinner = SpinnerLeaguesAdapter(context, leagues)
        spinner.adapter = adapterSpinner

        presenter.getLeagues()
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueId = leagues.get(position).id_league
                presenter.getTeams(leagueId)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(menu_search_teams, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val menuItem = menu.findItem(m_search)
        if (menuItem != null) {
            val searchView: SearchView = menuItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(query: String): Boolean {
                    if (query.isNotEmpty()) {
                        presenter.getQueriedTeams(query)
                    } else {
                        presenter.getTeams(leagueId)
                        spinner.visible()
                    }
                    return false
                }
            })
        }
        super.onPrepareOptionsMenu(menu)
    }

    override fun showLoading() {
        progressBar.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        recyclerView.visible()
    }

    override fun showTeamsList(teams: List<DataTeams>?) {
        this.teams.clear()
        if (teams != null) {
            this.teams.addAll(teams)
        } else {
            longToast(R.string.notavail)
        }
        adapterRecycler.notifyDataSetChanged()
    }

    override fun showLeaguesList(leagues: List<DataLeagues>) {
        this.leagues.clear()
        for (i in leagues) {
            if (i.league_sport.equals("Soccer")) {
                this.leagues.add(i)
            }
        }
        adapterSpinner.notifyDataSetChanged()
    }

    override fun hideSpinner() {
        spinner.invisible()
    }
}
