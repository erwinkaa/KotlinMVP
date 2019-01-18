package com.geolstudio.footballmatchschedulemvp.matches.searchmatches

import com.geolstudio.footballmatchschedulemvp.model.match.DataMatches

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<DataMatches>?)
}