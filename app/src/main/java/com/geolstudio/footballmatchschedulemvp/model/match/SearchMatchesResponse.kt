package com.geolstudio.footballmatchschedulemvp.model.match

import com.google.gson.annotations.SerializedName

class SearchMatchesResponse (@SerializedName("event") val matches: List<DataMatches>?)