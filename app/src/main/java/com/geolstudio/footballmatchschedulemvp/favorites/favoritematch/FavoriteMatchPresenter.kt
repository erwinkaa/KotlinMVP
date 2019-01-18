package com.geolstudio.footballmatchschedulemvp.favorites.favoritematch

import android.content.Context
import com.geolstudio.footballmatchschedulemvp.model.match.DataMatches
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import com.geolstudio.footballmatchschedulemvp.sqllite.database

class FavoriteMatchPresenter(private val view: FavoriteMatchView) {

    private var datas = mutableListOf<DataMatches>()

    fun getFavoriteMatch(ctx:Context?) {
        view.showLoading()

        ctx?.database?.use {
            datas.clear()
            val result = select(DataMatches.TABLE_MATCHES)
            val favorite = result.parseList(classParser<DataMatches>())
            datas.addAll(favorite)
        }

        view.hideLoading()
        view.showFavoriteMatchList(datas)
    }
}