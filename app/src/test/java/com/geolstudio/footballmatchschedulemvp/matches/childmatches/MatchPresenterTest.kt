package com.geolstudio.footballmatchschedulemvp.matches.childmatches

import com.geolstudio.footballmatchschedulemvp.api.ApiRepository
import com.geolstudio.footballmatchschedulemvp.api.TheSportDBApi
import com.geolstudio.footballmatchschedulemvp.coroutine.TestContextProvider
import com.geolstudio.footballmatchschedulemvp.model.leagues.DataLeagues
import com.geolstudio.footballmatchschedulemvp.model.leagues.DataLeaguesResponses
import com.geolstudio.footballmatchschedulemvp.model.match.DataMatches
import com.geolstudio.footballmatchschedulemvp.model.match.DataMatchesResponse
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchPresenterTest {
    private lateinit var presenter: MatchPresenter
    private var millis: Long = 1000

    @Mock
    private
    lateinit var view: MatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, gson, apiRepository, TestContextProvider())
    }

    @Test
    fun getNextMatches() {
        val dataMatches: MutableList<DataMatches> = mutableListOf()
        val response = DataMatchesResponse(dataMatches)
        val id = "4238"

        `when`(gson.fromJson(apiRepository
            .doRequest(TheSportDBApi.getNextMatch(id)),
            DataMatchesResponse::class.java
        )).thenReturn(response)

        presenter.getNextMatches(id)
        Thread.sleep(millis)

        verify(view).showLoading()
        verify(view).showMatchList(dataMatches)
        verify(view).hideLoading()
    }

    @Test
    fun getLastMatches() {
        val dataMatches: MutableList<DataMatches> = mutableListOf()
        val response = DataMatchesResponse(dataMatches)
        val id = "4238"

        `when`(gson.fromJson(apiRepository
            .doRequest(TheSportDBApi.getLastMatch(id)),
            DataMatchesResponse::class.java
        )).thenReturn(response)

        presenter.getLastMatches(id)
        Thread.sleep(millis)

        verify(view).showLoading()
        verify(view).showMatchList(dataMatches)
        verify(view).hideLoading()
    }

    @Test
    fun getLeagues() {
        val dataLeagues: MutableList<DataLeagues> = mutableListOf()
        val response = DataLeaguesResponses(dataLeagues)

        `when`(gson.fromJson(apiRepository
            .doRequest(TheSportDBApi.getLeagues()),
            DataLeaguesResponses::class.java
        )).thenReturn(response)

        presenter.getLeagues()
        Thread.sleep(millis)

        verify(view).showLoading()
        verify(view).showLeaguesList(dataLeagues)
    }
}