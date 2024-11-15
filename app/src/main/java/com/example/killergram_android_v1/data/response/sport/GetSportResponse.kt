package com.example.killergram_android_v1.data.response.sport

import com.google.gson.annotations.SerializedName

data class GetSportResponse(
    @SerializedName("sport_id") val sportId: String,
    @SerializedName("manager_email") val managerEmail: String,
    @SerializedName("sport_name") val sportName: String,
    @SerializedName("personnel") val personnel: Int,
    @SerializedName("current_personnel") val currentPersonnel: Int,
    @SerializedName("created_date") val createdDate: String,
    @SerializedName("enabled") val enabled: Boolean,
    @SerializedName("position") val position: Boolean,
)