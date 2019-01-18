package com.geolstudio.footballmatchschedulemvp.model.players

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataPlayers(
    @SerializedName("idPlayer") var player_id: String = "",
    @SerializedName("strPlayer") var player_name: String = "",
    @SerializedName("strDescriptionEN") var player_desc: String? = "",
    @SerializedName("strPosition") var player_position: String = "",
    @SerializedName("strHeight") var player_height: String = "",
    @SerializedName("strWeight") var player_weight: String = "",
    @SerializedName("strCutout") var player_thumbnail: String? = "",
    @SerializedName("strFanart1") var player_image: String? = ""
) : Parcelable