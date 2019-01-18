package com.geolstudio.footballmatchschedulemvp.model.teams

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataTeams(
        var id: Long? = -1,
        @SerializedName("idTeam") var team_id: String = "",
        @SerializedName("strTeam") var team_name: String = "",
        @SerializedName("intFormedYear") var team_year: String = "",
        @SerializedName("strStadium") var team_stadium: String = "",
        @SerializedName("strDescriptionEN") var team_desc: String = "",
        @SerializedName("strTeamBadge") var team_badge: String = ""
) : Parcelable {
    companion object {
        const val TABLE_TEAMS: String = "TEAM_TEAMS"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_YEAR: String = "TEAM_YEAR"
        const val TEAM_STADIUM: String = "TEAM_STADIUM"
        const val TEAM_DESC: String = "TEAM_DESC"
        const val TEAM_BADGE: String = "TEAM_BADGE"
    }
}