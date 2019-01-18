package com.geolstudio.footballmatchschedulemvp.matches.childmatches

import com.geolstudio.footballmatchschedulemvp.model.leagues.DataLeagues
import com.geolstudio.footballmatchschedulemvp.model.match.DataMatches

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<DataMatches>?)
    fun showLeaguesList(leagues: List<DataLeagues>)
}