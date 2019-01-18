package com.geolstudio.footballmatchschedulemvp.teams

import com.geolstudio.footballmatchschedulemvp.api.ApiRepository
import com.geolstudio.footballmatchschedulemvp.api.TheSportDBApi
import com.geolstudio.footballmatchschedulemvp.coroutine.TestContextProvider
import com.geolstudio.footballmatchschedulemvp.model.leagues.DataLeagues
import com.geolstudio.footballmatchschedulemvp.model.leagues.DataLeaguesResponses
import com.geolstudio.footballmatchschedulemvp.model.teams.DataTeams
import com.geolstudio.footballmatchschedulemvp.model.teams.DataTeamsResponse
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class TeamsPresenterTest {
    private lateinit var presenter: TeamsPresenter
    private var millis: Long = 1000

    @Mock
    private
    lateinit var view: TeamsView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamsPresenter(view, gson, apiRepository, TestContextProvider())
    }

    @Test
    fun getLeagues() {
        val dataLeagues: MutableList<DataLeagues> = mutableListOf()
        val response = DataLeaguesResponses(dataLeagues)

        `when`(
            gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getLeagues()),
                DataLeaguesResponses::class.java
            )
        ).thenReturn(response)

        presenter.getLeagues()
        Thread.sleep(millis)

        verify(view).showLoading()
        verify(view).showLeaguesList(dataLeagues)
    }

    @Test
    fun getTeams() {
        val dataTeams: MutableList<DataTeams> = mutableListOf()
        val response = DataTeamsResponse(dataTeams)
        val idLeague = "4238"

        `when`(gson.fromJson(apiRepository
            .doRequest(TheSportDBApi.getTeams(idLeague)),
            DataTeamsResponse::class.java
        )).thenReturn(response)

        presenter.getTeams(idLeague)
        Thread.sleep(1000)

        verify(view).showLoading()
        verify(view).showTeamsList(dataTeams)
        verify(view).hideLoading()
    }

    @Test
    fun getQueriedTeams() {
        val dataTeams: MutableList<DataTeams> = mutableListOf()
        val response = DataTeamsResponse(dataTeams)
        val query = "barcelona"

        `when`(gson.fromJson(apiRepository
            .doRequest(TheSportDBApi.getQueriedTeams(query)),
            DataTeamsResponse::class.java
        )).thenReturn(response)

        presenter.getQueriedTeams(query)
        Thread.sleep(1000)

        verify(view).showLoading()
        verify(view).showTeamsList(dataTeams)
        verify(view).hideLoading()
    }
}