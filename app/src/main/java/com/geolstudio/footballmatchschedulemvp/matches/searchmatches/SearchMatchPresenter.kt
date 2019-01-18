package com.geolstudio.footballmatchschedulemvp.matches.searchmatches

import com.geolstudio.footballmatchschedulemvp.api.ApiRepository
import com.geolstudio.footballmatchschedulemvp.api.TheSportDBApi
import com.geolstudio.footballmatchschedulemvp.coroutine.CoroutineContextProvider
import com.geolstudio.footballmatchschedulemvp.model.match.SearchMatchesResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchMatchPresenter(
        private val view: SearchMatchView,
        private val gson: Gson,
        private val apiRepository: ApiRepository,
        private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getQueriedMatch(query: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getQueriedMatch(query)), SearchMatchesResponse::class.java)
            }
            view.showMatchList(data.await().matches)
            view.hideLoading()
        }
    }

}