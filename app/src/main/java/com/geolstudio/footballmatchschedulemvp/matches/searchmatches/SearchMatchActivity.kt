package com.geolstudio.footballmatchschedulemvp.matches.searchmatches

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ProgressBar
import com.geolstudio.aplikasifootballclub.SearchMatchAdapter
import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.R.id.m_search
import com.geolstudio.footballmatchschedulemvp.R.menu.menu_search_teams
import com.geolstudio.footballmatchschedulemvp.api.ApiRepository
import com.geolstudio.footballmatchschedulemvp.model.match.DataMatches
import com.geolstudio.footballmatchschedulemvp.util.invisible
import com.geolstudio.footballmatchschedulemvp.util.visible
import com.google.gson.Gson

class SearchMatchActivity : AppCompatActivity(), SearchMatchView {

    private var datas = mutableListOf<DataMatches>()
    private lateinit var presenter: SearchMatchPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: SearchMatchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        progressBar = findViewById(R.id.progress_search)
        recyclerView = findViewById(R.id.recycler_match)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        adapter = SearchMatchAdapter(applicationContext, datas) // tidak pake listener intent matchdetail karena waktu2nya berbeda bikin crash saat converting
        recyclerView.adapter = adapter

        val gson = Gson()
        val apiRepository = ApiRepository()
        presenter = SearchMatchPresenter(this, gson, apiRepository)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        supportActionBar?.setTitle(R.string.search)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_search_teams, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) : Boolean {
        val menuItem = menu.findItem(m_search)
        if (menuItem != null) {
            val searchView: SearchView = menuItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(query: String): Boolean {
                    if (query.trim().isNotEmpty()) {
                        presenter.getQueriedMatch(query)
                    } else if (query.trim().isEmpty()) {
                        datas.clear()
                        adapter.notifyDataSetChanged()
                    }
                    return false
                }
            })
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        progressBar.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        recyclerView.visible()
    }

    override fun showMatchList(data: List<DataMatches>?) {
        datas.clear()
        if (data != null) {
            datas.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }
}
