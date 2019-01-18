package com.geolstudio.footballmatchschedulemvp.matches.childmatches


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.Spinner

import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.api.ApiRepository
import com.geolstudio.footballmatchschedulemvp.matchdetail.MatchDetailActivity
import com.geolstudio.footballmatchschedulemvp.model.leagues.DataLeagues
import com.geolstudio.footballmatchschedulemvp.model.match.DataMatches
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import android.provider.CalendarContract.Events
import android.provider.CalendarContract
import android.content.Intent
import com.geolstudio.footballmatchschedulemvp.util.*


class MatchFragment : Fragment(), MatchView {

    private var key = "TYPE"
    private var datas = mutableListOf<DataMatches>()
    private var leagues = mutableListOf<DataLeagues>()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var adapterSpinner: SpinnerLeaguesAdapter
    private lateinit var leagueId: String


    fun newInstance(type: String): MatchFragment {
        val args = Bundle()
        args.putSerializable(key, type)
        val fragment = MatchFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_match, container, false)

        swipeRefreshLayout = rootView.findViewById(R.id.refresh_match)
        progressBar = rootView.findViewById(R.id.progress_match)
        spinner = rootView.findViewById(R.id.spinner)
        recyclerView = rootView.findViewById(R.id.recycler_match)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val type = arguments?.getSerializable(key) as String

        adapterSpinner = SpinnerLeaguesAdapter(context, leagues)
        spinner.adapter = adapterSpinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueId = leagues.get(position).id_league
                if (type.equals("next")) {
                    presenter.getNextMatches(leagueId)
                } else if (type.equals("last")) {
                    presenter.getLastMatches(leagueId)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        adapter = MatchAdapter(context, datas, type, {
            startActivity<MatchDetailActivity>("data" to it)
        }, {
            val intent = Intent(Intent.ACTION_INSERT)
            intent.type = "vnd.android.cursor.item/event"

            val affectedDatebyTime = (it.date_event + " " + it.time_event).toSimpleLocaleDateString()
            val timeGMT7 = it.time_event.toLocaleTimeString()

            val strDateTime = (affectedDatebyTime + " " + timeGMT7).getDateTimeMillis()

            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, strDateTime)
            intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)

            val title = it.team1_name + " vs " + it.team2_name
            intent.putExtra(Events.TITLE, title)
            intent.putExtra(Events.DESCRIPTION, "Jangan lupa nonton!!!")
            intent.putExtra(Events.EVENT_LOCATION, "Dirumah bossss")
            intent.putExtra(Events.RRULE, "FREQ=DAILY")

            startActivity(intent)
        })

        recyclerView.adapter = adapter

        val gson = Gson()
        val apiRepository = ApiRepository()
        presenter = MatchPresenter(this, gson, apiRepository)

        presenter.getLeagues()

        swipeRefreshLayout.onRefresh {
            if (type.equals("next")) {
                presenter.getNextMatches(leagueId)
            } else if (type.equals("last")) {
                presenter.getLastMatches(leagueId)
            }
        }

        return rootView
    }

    override fun showLoading() {
        if (!swipeRefreshLayout.isRefreshing) {
            progressBar.visible()
        }
        recyclerView.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        recyclerView.visible()
    }

    override fun showMatchList(data: List<DataMatches>?) {
        swipeRefreshLayout.isRefreshing = false
        datas.clear()
        if (data != null) {
            datas.addAll(data)
        } else {
            longToast(R.string.notavail)
        }
        adapter.notifyDataSetChanged()
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
}
