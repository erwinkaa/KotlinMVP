package com.geolstudio.footballmatchschedulemvp.model.players

import com.google.gson.annotations.SerializedName

class DataPlayersResponse (@SerializedName("player") val players: List<DataPlayers>)