package com.geolstudio.footballmatchschedulemvp.model.match

import com.google.gson.annotations.SerializedName

class DataMatchesResponse (@SerializedName("events") val matches: List<DataMatches>?)