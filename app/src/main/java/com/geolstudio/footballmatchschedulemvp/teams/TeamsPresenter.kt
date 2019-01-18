package com.geolstudio.footballmatchschedulemvp.teams

import com.geolstudio.footballmatchschedulemvp.api.ApiRepository
import com.geolstudio.footballmatchschedulemvp.api.TheSportDBApi
import com.geolstudio.footballmatchschedulemvp.coroutine.CoroutineContextProvider
import com.geolstudio.footballmatchschedulemvp.model.leagues.DataLeaguesResponses
import com.geolstudio.footballmatchschedulemvp.model.teams.DataTeamsResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter(
    private val view: TeamsView,
    private val gson: Gson,
    private val apiRepository: ApiRepository,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLeagues() {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getLeagues()), DataLeaguesResponses::class.java)
            }
            view.showLeaguesList(data.await().leagues)
        }
    }

    fun getTeams(id: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeams(id)), DataTeamsResponse::class.java)
            }
            view.showTeamsList(data.await().teams)
            view.hideLoading()
        }
    }

    fun getQueriedTeams(query: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getQueriedTeams(query)), DataTeamsResponse::class.java)
            }
            view.showTeamsList(data.await().teams)
            view.hideLoading()
            view.hideSpinner()
        }
    }

}