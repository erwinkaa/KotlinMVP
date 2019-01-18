package com.geolstudio.footballmatchschedulemvp.model.match

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataMatches(
    var id: Long? = -1,
    @SerializedName("idEvent") var id_event: String = "",
    @SerializedName("dateEvent") var date_event: String = "",
    @SerializedName("strTime") var time_event: String = "",

    @SerializedName("idHomeTeam") var team1_id: String? = "",
    @SerializedName("strHomeTeam") var team1_name: String? = "",
    var team1_badge: String = "",
    @SerializedName("strHomeGoalDetails") var team1_goaldetail: String? = "",
    @SerializedName("strHomeLineupGoalkeeper") var team1_gk: String? = "",
    @SerializedName("strHomeLineupDefense") var team1_def: String? = "",
    @SerializedName("strHomeLineupMidfield") var team1_mid: String? = "",
    @SerializedName("strHomeLineupForward") var team1_fw: String? = "",
    @SerializedName("strHomeLineupSubstitutes") var team1_sub: String? = "",
    @SerializedName("intHomeShots") var team1_shot: String? = "",
    @SerializedName("intHomeScore") var team1_score: String? = "",

    @SerializedName("idAwayTeam") var team2_id: String? = "",
    @SerializedName("strAwayTeam") var team2_name: String? = "",
    var team2_badge: String = "",
    @SerializedName("strAwayGoalDetails") var team2_goaldetail: String? = "",
    @SerializedName("strAwayLineupGoalkeeper") var team2_gk: String? = "",
    @SerializedName("strAwayLineupDefense") var team2_def: String? = "",
    @SerializedName("strAwayLineupMidfield") var team2_mid: String? = "",
    @SerializedName("strAwayLineupForward") var team2_fw: String? = "",
    @SerializedName("strAwayLineupSubstitutes") var team2_sub: String? = "",
    @SerializedName("intAwayShots") var team2_shot: String? = "",
    @SerializedName("intAwayScore") var team2_score: String? = ""
) : Parcelable {

    companion object {
        const val TABLE_MATCHES: String = "TABLE_MATCHES"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "EVENT_ID"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val TIME_EVENT: String = "TIME_EVENT"

        const val TEAM1_ID: String = "TEAM1_ID"
        const val TEAM1_NAME: String = "TEAM1_NAME"
        const val TEAM1_BADGE: String = "TEAM1_BADGE"
        const val TEAM1_GOALDETAIL: String = "TEAM1_GOALDETAIL"
        const val TEAM1_GK: String = "TEAM1_GK"
        const val TEAM1_DEF: String = "TEAM1_DEF"
        const val TEAM1_MID: String = "TEAM1_MID"
        const val TEAM1_FW: String = "TEAM1_FW"
        const val TEAM1_SUB: String = "TEAM1_SUB"
        const val TEAM1_SHOT: String = "TEAM1_SHOT"
        const val TEAM1_SCORE: String = "TEAM1_SCORE"

        const val TEAM2_ID: String = "TEAM2_ID"
        const val TEAM2_NAME: String = "TEAM2_NAME"
        const val TEAM2_BADGE: String = "TEAM2_BADGE"
        const val TEAM2_GOALDETAIL: String = "TEAM2_GOALDETAIL"
        const val TEAM2_GK: String = "TEAM2_GK"
        const val TEAM2_DEF: String = "TEAM2_DEF"
        const val TEAM2_MID: String = "TEAM2_MID"
        const val TEAM2_FW: String = "TEAM2_FW"
        const val TEAM2_SUB: String = "TEAM2_SUB"
        const val TEAM2_SHOT: String = "TEAM2_SHOT"
        const val TEAM2_SCORE: String = "TEAM2_SCORE"
    }
}