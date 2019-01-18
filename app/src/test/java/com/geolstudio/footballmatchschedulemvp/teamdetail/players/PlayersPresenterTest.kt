package com.geolstudio.footballmatchschedulemvp.teamdetail.players

import com.geolstudio.footballmatchschedulemvp.api.ApiRepository
import com.geolstudio.footballmatchschedulemvp.api.TheSportDBApi
import com.geolstudio.footballmatchschedulemvp.coroutine.TestContextProvider
import com.geolstudio.footballmatchschedulemvp.model.players.DataPlayers
import com.geolstudio.footballmatchschedulemvp.model.players.DataPlayersResponse
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PlayersPresenterTest {
    private lateinit var presenter: PlayersPresenter
    private var millis: Long = 1000

    @Mock
    private
    lateinit var view: PlayersView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayersPresenter(view, gson, apiRepository, TestContextProvider())
    }
    @Test
    fun getPlayersTeam() {
        val dataPlayers: MutableList<DataPlayers> = mutableListOf()
        val response = DataPlayersResponse(dataPlayers)
        val idTeam = "133619"

        `when`(gson.fromJson(apiRepository
            .doRequest(TheSportDBApi.getPlayersTeam(idTeam)),
            DataPlayersResponse::class.java
        )).thenReturn(response)

        presenter.getPlayersTeam(idTeam)
        Thread.sleep(millis)

        verify(view).showLoading()
        verify(view).showPlayersList(dataPlayers)
        verify(view).hideLoading()
    }
}