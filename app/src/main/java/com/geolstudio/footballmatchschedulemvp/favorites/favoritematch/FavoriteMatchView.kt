package com.geolstudio.footballmatchschedulemvp.favorites.favoritematch

import com.geolstudio.footballmatchschedulemvp.model.match.DataMatches

interface FavoriteMatchView {
    fun showLoading()
    fun hideLoading()
    fun showFavoriteMatchList(data: List<DataMatches>)
}