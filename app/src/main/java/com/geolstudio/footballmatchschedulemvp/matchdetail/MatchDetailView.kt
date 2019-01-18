package com.geolstudio.footballmatchschedulemvp.matchdetail

interface MatchDetailView {
    fun showLoadingTeam1()
    fun hideLoadingTeam1(imgUrl:String)
    fun showLoadingTeam2()
    fun hideLoadingTeam2(imgUrl:String)
    fun showNotFoundImage1()
    fun showNotFoundImage2()
}