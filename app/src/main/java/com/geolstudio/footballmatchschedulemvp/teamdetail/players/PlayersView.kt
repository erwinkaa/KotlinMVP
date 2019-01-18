package com.geolstudio.footballmatchschedulemvp.teamdetail.players

import com.geolstudio.footballmatchschedulemvp.model.players.DataPlayers

interface PlayersView {
    fun showLoading()
    fun hideLoading()
    fun showPlayersList(players: List<DataPlayers>)
}