package com.geolstudio.footballmatchschedulemvp.matchdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.R.drawable.ic_star_white_24dp
import com.geolstudio.footballmatchschedulemvp.R.drawable.ic_star_border_white_24dp
import com.geolstudio.footballmatchschedulemvp.R.id.add_to_favorite
import com.geolstudio.footballmatchschedulemvp.R.menu.menu_favorite
import com.geolstudio.footballmatchschedulemvp.model.match.DataMatches
import com.geolstudio.footballmatchschedulemvp.util.*
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.toast
import java.lang.Exception

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var data: DataMatches
    private lateinit var presenter: MatchDetailPresenter
    private lateinit var urlImg1: String
    private lateinit var urlImg2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        data = intent.getParcelableExtra("data")

        presenter = MatchDetailPresenter(this)
        presenter.getTeamLogoTeam1(data.team1_id)
        presenter.getTeamLogoTeam2(data.team2_id)
        showMatchDetail(data)

        isFavorite = presenter.favoriteState(this, data.id_event)
    }

    fun showMatchDetail(data: DataMatches) {
        val affectedDatebyTime = (data.date_event + " " + data.time_event).toSimpleLocaleDateString()
        tv_event_date.text = affectedDatebyTime
        tv_event_time.text = data.time_event.toLocaleTimeString()
        tv_team1.text = data.team1_name
        tv_team2.text = data.team2_name
        tv_score_team1.text = data.team1_score
        tv_score_team2.text = data.team2_score

        var goaldetail1 = ""
        if (data.team1_goaldetail != null) {
            val splitgoaldetail1 = data.team1_goaldetail!!.split(";")
            for (i in 0..(splitgoaldetail1.size - 1)) {
                goaldetail1 += splitgoaldetail1[i] + "\n"
            }
        }
        tv_goaldetailteam1.text = goaldetail1

        var goaldetail2 = ""
        if (data.team2_goaldetail != null) {
            val splitgoaldetail2 = data.team2_goaldetail!!.split(";")
            for (i in 0..(splitgoaldetail2.size - 1)) {
                goaldetail2 += splitgoaldetail2[i] + "\n"
            }
        }
        tv_goaldetailteam2.text = goaldetail2

        var shotteam1 = ""
        if (data.team1_shot != null) {
            val splitshot1 = data.team1_shot!!.split(";")
            for (i in 0..(splitshot1.size - 1)) {
                shotteam1 += splitshot1[i] + "\n"
            }
        }
        tv_shotteam1.text = shotteam1

        var shotteam2 = ""
        if (data.team2_shot != null) {
            val splitshot2 = data.team2_shot!!.split(";")
            for (i in 0..(splitshot2.size - 1)) {
                shotteam2 += splitshot2[i] + "\n"
            }
        }
        tv_shotteam2.text = shotteam2

        var gkteam1 = ""
        if (data.team1_gk != null) {
            val splitgk1 = data.team1_gk!!.split(";")
            for (i in 0..(splitgk1.size - 1)) {
                gkteam1 += splitgk1[i] + "\n"
            }
        }
        tv_gkteam1.text = gkteam1

        var gkteam2 = ""
        if (data.team2_gk != null) {
            val splitgk2 = data.team2_gk!!.split(";")
            for (i in 0..(splitgk2.size - 1)) {
                gkteam2 += splitgk2[i] + "\n"
            }
        }
        tv_gkteam2.text = gkteam2

        var defteam1 = ""
        if (data.team1_def != null) {
            val splitdef1 = data.team1_def!!.split(";")
            for (i in 0..(splitdef1.size - 1)) {
                defteam1 += splitdef1[i] + "\n"
            }
        }
        tv_defteam1.text = defteam1

        var defteam2 = ""
        if (data.team2_def != null) {
            val splitdef2 = data.team2_def!!.split(";")
            for (i in 0..(splitdef2.size - 1)) {
                defteam2 += splitdef2[i] + "\n"
            }
        }
        tv_defteam2.text = defteam2

        var midteam1 = ""
        if (data.team1_mid != null) {
            val splitmid1 = data.team1_mid!!.split(";")
            for (i in 0..(splitmid1.size - 1)) {
                midteam1 += splitmid1[i] + "\n"
            }
        }
        tv_midteam1.text = midteam1

        var midteam2 = ""
        if (data.team2_mid != null) {
            val splitmid2 = data.team2_mid!!.split(";")
            for (i in 0..(splitmid2.size - 1)) {
                midteam2 += splitmid2[i] + "\n"
            }
        }
        tv_midteam2.text = midteam2

        var fwteam1 = ""
        if (data.team1_fw != null) {
            val splitfw1 = data.team1_fw!!.split(";")
            for (i in 0..(splitfw1.size - 1)) {
                fwteam1 += splitfw1[i] + "\n"

            }
        }
        tv_fwteam1.text = fwteam1

        var fwteam2 = ""
        if (data.team2_fw != null) {
            val splitfw2 = data.team2_fw!!.split(";")
            for (i in 0..(splitfw2.size - 1)) {
                fwteam2 += splitfw2[i] + "\n"
            }
        }
        tv_fwteam2.text = fwteam2

        var subteam1 = ""
        if (data.team1_sub != null) {
            val splitsub1 = data.team1_sub!!.split(";")
            for (i in 0..(splitsub1.size - 1)) {
                subteam1 += splitsub1[i] + "\n"
            }
        }
        tv_subteam1.text = subteam1

        var subteam2 = ""
        if (data.team2_sub != null) {
            val splitsub2 = data.team2_sub!!.split(";")
            for (i in 0..(splitsub2.size - 1)) {
                subteam2 += splitsub2[i] + "\n"
            }
        }
        tv_subteam2.text = subteam2
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) {
                    presenter.removeFromFavorite(this, data.id_event)
                    isFavorite = !isFavorite
                    setFavorite()
                } else {
                    if (!data.team1_name.isNullOrEmpty() || !data.team2_name.isNullOrEmpty()) {
                        presenter.addToFavorite(this, data, urlImg1, urlImg2)
                        isFavorite = !isFavorite
                        setFavorite()
                    } else {
                        toast(R.string.notavail)
                    }
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_favorite, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_star_white_24dp)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_star_border_white_24dp)
    }

    override fun showLoadingTeam1() {
        progress_team1.visible()
    }

    override fun hideLoadingTeam1(imgUrl: String) {
        progress_team1.invisible()
        urlImg1 = imgUrl
        Glide.with(this).load(imgUrl).into(iv_team1)
    }

    override fun showLoadingTeam2() {
        progress_team2.visible()
    }

    override fun hideLoadingTeam2(imgUrl: String) {
        progress_team2.invisible()
        urlImg2 = imgUrl
        Glide.with(this).load(imgUrl).into(iv_team2)
    }

    override fun showNotFoundImage1() {
        progress_team1.invisible()
        Glide.with(this).load(R.drawable.notfound).into(iv_team1)
    }

    override fun showNotFoundImage2() {
        progress_team2.invisible()
        Glide.with(this).load(R.drawable.notfound).into(iv_team2)
    }
}
