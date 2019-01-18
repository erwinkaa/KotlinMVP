package com.geolstudio.footballmatchschedulemvp.favorites.favoriteteams

import com.geolstudio.footballmatchschedulemvp.model.teams.DataTeams

interface FavoriteTeamView {
    fun showLoading()
    fun hideLoading()
    fun showFavoriteTeamList(team: List<DataTeams>)
}