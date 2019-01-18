package com.geolstudio.footballmatchschedulemvp.teams

import com.geolstudio.footballmatchschedulemvp.model.leagues.DataLeagues
import com.geolstudio.footballmatchschedulemvp.model.teams.DataTeams

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamsList(teams: List<DataTeams>?)
    fun showLeaguesList(leagues: List<DataLeagues>)
    fun hideSpinner()
}