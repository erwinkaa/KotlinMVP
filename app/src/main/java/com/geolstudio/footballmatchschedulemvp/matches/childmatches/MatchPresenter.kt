package com.geolstudio.footballmatchschedulemvp.matches.childmatches

import com.geolstudio.footballmatchschedulemvp.api.ApiRepository
import com.geolstudio.footballmatchschedulemvp.api.TheSportDBApi
import com.geolstudio.footballmatchschedulemvp.coroutine.CoroutineContextProvider
import com.geolstudio.footballmatchschedulemvp.model.leagues.DataLeaguesResponses
import com.geolstudio.footballmatchschedulemvp.model.match.DataMatchesResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchPresenter(
    private val view: MatchView,
    private val gson: Gson,
    private val apiRepository: ApiRepository,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getNextMatches(id: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextMatch(id)), DataMatchesResponse::class.java)
            }
            view.showMatchList(data.await().matches)
            view.hideLoading()
        }
    }

    fun getLastMatches(id: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getLastMatch(id)), DataMatchesResponse::class.java)
            }
            view.showMatchList(data.await().matches)
            view.hideLoading()
        }
    }

    fun getLeagues() {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getLeagues()), DataLeaguesResponses::class.java)
            }
            view.showLeaguesList(data.await().leagues)
        }
    }

}