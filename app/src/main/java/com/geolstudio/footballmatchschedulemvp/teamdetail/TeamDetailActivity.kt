package com.geolstudio.footballmatchschedulemvp.teamdetail

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.model.teams.DataTeams
import com.geolstudio.footballmatchschedulemvp.teamdetail.overview.OverviewFragment
import com.geolstudio.footballmatchschedulemvp.teamdetail.players.PlayersFragment
import com.geolstudio.footballmatchschedulemvp.util.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamDetailActivity : AppCompatActivity() {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var data: DataTeams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        data = intent.getParcelableExtra("data")

        Glide.with(applicationContext).load(data.team_badge).into(iv_team_badge)
        tv_team_name.text = data.team_name
        val addedEst = "est. " + data.team_year
        tv_team_year.text = addedEst
        tv_team_stadium.text = data.team_stadium

        val collapsingToolbarLayout = toolbar_layout
        val appBarLayout = appbar
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            private var isShow = true
            private var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.title = data.team_name
                    isShow = true
                } else if (isShow) {
                    collapsingToolbarLayout.title = " "
                    isShow = false
                }
            }

        })

        presenter = TeamDetailPresenter()

        viewPager = findViewById(R.id.vp_teamdetail)
        tabLayout = findViewById(R.id.tablayout)
        adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(OverviewFragment().newInstance(data.team_desc))
        adapter.addFragment(PlayersFragment().newInstance(data.team_id))

        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })

        isFavorite = presenter.favoriteState(this, data.team_id)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) {
                    presenter.removeFromFavorite(this, data.team_id)
                    isFavorite = !isFavorite
                    setFavorite()
                } else {
                    presenter.addToFavorite(this, data)
                    isFavorite = !isFavorite
                    setFavorite()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_white_24dp)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_white_24dp)
    }
}
