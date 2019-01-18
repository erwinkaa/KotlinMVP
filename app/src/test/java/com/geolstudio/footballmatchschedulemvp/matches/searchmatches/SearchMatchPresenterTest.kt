package com.geolstudio.footballmatchschedulemvp.matches.searchmatches

import com.geolstudio.footballmatchschedulemvp.api.ApiRepository
import com.geolstudio.footballmatchschedulemvp.api.TheSportDBApi
import com.geolstudio.footballmatchschedulemvp.coroutine.TestContextProvider
import com.geolstudio.footballmatchschedulemvp.model.match.DataMatches
import com.geolstudio.footballmatchschedulemvp.model.match.DataMatchesResponse
import com.geolstudio.footballmatchschedulemvp.model.match.SearchMatchesResponse
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchMatchPresenterTest {
    private lateinit var presenter: SearchMatchPresenter
    private var millis: Long = 1000

    @Mock
    private
    lateinit var view: SearchMatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchPresenter(view, gson, apiRepository, TestContextProvider())
    }
    @Test
    fun getQueriedMatch() {
        val dataMatches: MutableList<DataMatches> = mutableListOf()
        val response = SearchMatchesResponse(dataMatches)
        val query = "manchester"

        `when`(gson.fromJson(apiRepository
            .doRequest(TheSportDBApi.getQueriedMatch(query)),
            SearchMatchesResponse::class.java
        )).thenReturn(response)

        presenter.getQueriedMatch(query)
        Thread.sleep(millis)

        verify(view).showLoading()
        verify(view).showMatchList(dataMatches)
        verify(view).hideLoading()
    }
}