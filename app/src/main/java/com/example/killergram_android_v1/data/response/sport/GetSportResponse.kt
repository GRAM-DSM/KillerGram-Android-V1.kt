package com.example.killergram_android_v1.data.response.sport

import com.google.gson.annotations.SerializedName

data class GetSportResponsee(
    @SerializedName("sport_id") val sport_id: String,
    @SerializedName("manager_email") val manager_email: String,
    @SerializedName("sport_name") val sport_name: String,
    @SerializedName("personnel") val personnel: Int,
    @SerializedName("current_personnel") val current_personnel: Int,
    @SerializedName("created_date") val created_date: String,
    @SerializedName("position") val position: Boolean,
    
    @SerializedName("enabled") val enabled: Boolean,
)