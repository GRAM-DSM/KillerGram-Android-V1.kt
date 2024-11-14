package com.example.killergram_android_v1.data.response.sport

data class GetSportResponse(
    val created_date: String,
    val manager_email: String,
    val personnel: Int,
    val position: Boolean,
    val sport_id: String,
    val sport_name: String
)