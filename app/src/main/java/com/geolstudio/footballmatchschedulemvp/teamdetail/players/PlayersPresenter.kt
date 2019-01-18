package com.geolstudio.footballmatchschedulemvp.teamdetail.players

import com.geolstudio.footballmatchschedulemvp.api.ApiRepository
import com.geolstudio.footballmatchschedulemvp.api.TheSportDBApi
import com.geolstudio.footballmatchschedulemvp.coroutine.CoroutineContextProvider
import com.geolstudio.footballmatchschedulemvp.model.players.DataPlayersResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayersPresenter (
    private val view: PlayersView,
    private val gson: Gson,
    private val apiRepository: ApiRepository,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getPlayersTeam(id: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPlayersTeam(id)), DataPlayersResponse::class.java)
            }
            view.showPlayersList(data.await().players)
            view.hideLoading()
        }
    }
}