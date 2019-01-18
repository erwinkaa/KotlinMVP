package com.geolstudio.footballmatchschedulemvp.model.leagues

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataLeagues
(
        @SerializedName("idLeague") var id_league: String = "",
        @SerializedName("strLeague") var league_name: String = "",
        @SerializedName("strSport") var league_sport: String = ""
) : Parcelable