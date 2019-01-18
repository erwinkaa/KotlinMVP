package com.geolstudio.footballmatchschedulemvp.teamdetail

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.model.teams.DataTeams
import com.geolstudio.footballmatchschedulemvp.sqllite.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class TeamDetailPresenter {

    fun addToFavorite(ctx: Context?, data: DataTeams) {
        try {
            ctx?.database?.use {
                insert(
                        DataTeams.TABLE_TEAMS,
                        DataTeams.TEAM_ID to data.team_id,
                        DataTeams.TEAM_NAME to data.team_name,
                        DataTeams.TEAM_YEAR to data.team_year,
                        DataTeams.TEAM_STADIUM to data.team_stadium,
                        DataTeams.TEAM_DESC to data.team_desc,
                        DataTeams.TEAM_BADGE to data.team_badge
                )
            }
            ctx?.toast(R.string.TOAST_FAVORITE)?.show()
        } catch (e: SQLiteConstraintException) {
            Log.e("GDK", e.message)
            ctx?.toast(e.localizedMessage)?.show()
        }
    }

    fun removeFromFavorite(ctx: Context?, team_id: String) {
        try {
            ctx?.database?.use {
                delete(
                        DataTeams.TABLE_TEAMS,
                        "(TEAM_ID = {id})",
                        "id" to team_id
                )
            }
            ctx?.toast(R.string.TOAST_UNFAVORITE)?.show()
        } catch (e: SQLiteConstraintException) {
            Log.e("GDK", e.message)
            ctx?.toast(e.localizedMessage)?.show()
        }
    }

    fun favoriteState(ctx: Context?, team_id: String): Boolean {
        var isFavorited = false

        ctx?.database?.use {
            val result = select(DataTeams.TABLE_TEAMS)
                    .whereArgs(
                            "(TEAM_ID = {id})",
                            "id" to team_id
                    )
            val favorite = result.parseList(classParser<DataTeams>())
            if (!favorite.isEmpty()) {
                isFavorited = true
            }
        }
        return isFavorited
    }

}