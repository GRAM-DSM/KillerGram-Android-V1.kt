package com.example.killergram_android_v1.data.response.sport

import com.google.gson.annotations.SerializedName

data class GetSportResponse(
    @SerializedName("created_date") val createdDate: String,
    @SerializedName("manager_email") val managerEmail: String,
    @SerializedName("personnel") val personnel: Int,
    @SerializedName("position") val position: Boolean,
    @SerializedName("sport_id") val sportId: String,
    @SerializedName("sport_name") val sportName: String
)