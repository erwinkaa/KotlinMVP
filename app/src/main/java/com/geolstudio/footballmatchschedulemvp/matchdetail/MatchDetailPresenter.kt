package com.geolstudio.footballmatchschedulemvp.matchdetail

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.api.TheSportDBApi
import com.geolstudio.footballmatchschedulemvp.model.match.DataMatches
import com.geolstudio.footballmatchschedulemvp.sqllite.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import org.json.JSONObject

class MatchDetailPresenter(private val view: MatchDetailView) {

    fun getTeamLogoTeam1(id: String?) {

        view.showLoadingTeam1()

        if (id != null) {
            AndroidNetworking.get(TheSportDBApi.getTeamDetail(id))
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        val jsonArray = response.getJSONArray("teams")
                        val imgUrl = jsonArray.getJSONObject(0).getString("strTeamBadge")

                        view.hideLoadingTeam1(imgUrl)
                    }

                    override fun onError(anError: ANError) {
                        Log.e("GDK", anError.errorBody)
                    }
                })
        } else {
            view.showNotFoundImage1()
        }
    }

    fun getTeamLogoTeam2(id: String?) {
        view.showLoadingTeam2()

        if (id != null) {
            AndroidNetworking.get(TheSportDBApi.getTeamDetail(id))
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        val jsonArray = response.getJSONArray("teams")
                        val imgUrl = jsonArray.getJSONObject(0).getString("strTeamBadge")

                        view.hideLoadingTeam2(imgUrl)
                    }

                    override fun onError(anError: ANError) {
                        Log.e("GDK", anError.errorBody)
                    }
                })
        } else {
            view.showNotFoundImage2()
        }
    }

    fun addToFavorite(ctx: Context?, data: DataMatches, imgteam1: String, imgteam2: String) {
        try {
            ctx?.database?.use {
                insert(
                    DataMatches.TABLE_MATCHES,
                    DataMatches.ID_EVENT to data.id_event,
                    DataMatches.DATE_EVENT to data.date_event,
                    DataMatches.TIME_EVENT to data.time_event,

                    DataMatches.TEAM1_ID to data.team1_id,
                    DataMatches.TEAM1_NAME to data.team1_name,
                    DataMatches.TEAM1_GOALDETAIL to data.team1_goaldetail,
                    DataMatches.TEAM1_BADGE to imgteam1,
                    DataMatches.TEAM1_GK to data.team1_gk,
                    DataMatches.TEAM1_DEF to data.team1_def,
                    DataMatches.TEAM1_MID to data.team1_mid,
                    DataMatches.TEAM1_FW to data.team1_fw,
                    DataMatches.TEAM1_SUB to data.team1_sub,
                    DataMatches.TEAM1_SHOT to data.team1_shot,
                    DataMatches.TEAM1_SCORE to data.team1_score,

                    DataMatches.TEAM2_ID to data.team2_id,
                    DataMatches.TEAM2_NAME to data.team2_name,
                    DataMatches.TEAM2_GOALDETAIL to data.team2_goaldetail,
                    DataMatches.TEAM2_BADGE to imgteam2,
                    DataMatches.TEAM2_GK to data.team2_gk,
                    DataMatches.TEAM2_DEF to data.team2_def,
                    DataMatches.TEAM2_MID to data.team2_mid,
                    DataMatches.TEAM2_FW to data.team2_fw,
                    DataMatches.TEAM2_SUB to data.team2_sub,
                    DataMatches.TEAM2_SHOT to data.team2_shot,
                    DataMatches.TEAM2_SCORE to data.team2_score
                )
            }
            ctx?.toast(R.string.TOAST_FAVORITE)?.show()
        } catch (e: SQLiteConstraintException) {
            Log.e("GDK", e.message)
            ctx?.toast(e.localizedMessage)?.show()
        }
    }

    fun removeFromFavorite(ctx: Context?, event_id: String) {
        try {
            ctx?.database?.use {
                delete(
                    DataMatches.TABLE_MATCHES,
                    "(EVENT_ID = {id})",
                    "id" to event_id
                )
            }
            ctx?.toast(R.string.TOAST_UNFAVORITE)?.show()
        } catch (e: SQLiteConstraintException) {
            Log.e("GDK", e.message)
            ctx?.toast(e.localizedMessage)?.show()
        }
    }

    fun favoriteState(ctx: Context?, event_id: String): Boolean {
        var isFavorited = false

        ctx?.database?.use {
            val result = select(DataMatches.TABLE_MATCHES)
                .whereArgs(
                    "(EVENT_ID = {id})",
                    "id" to event_id
                )
            val favorite = result.parseList(classParser<DataMatches>())
            if (!favorite.isEmpty()) {
                isFavorited = true
            }
        }
        return isFavorited
    }
}