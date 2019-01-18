package com.geolstudio.footballmatchschedulemvp.favorites.favoriteteams

import android.content.Context
import com.geolstudio.footballmatchschedulemvp.model.teams.DataTeams
import com.geolstudio.footballmatchschedulemvp.sqllite.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamPresenter(private val view: FavoriteTeamView) {

    private var datas = mutableListOf<DataTeams>()

    fun getFavoriteMatch(ctx: Context?) {
        view.showLoading()

        ctx?.database?.use {
            datas.clear()
            val result = select(DataTeams.TABLE_TEAMS)
            val favorite = result.parseList(classParser<DataTeams>())
            datas.addAll(favorite)
        }

        view.hideLoading()
        view.showFavoriteTeamList(datas)
    }
}