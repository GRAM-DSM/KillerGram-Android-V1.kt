package com.example.killergram_android_v1.data.request.auth.signup

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    val ability: String,
    @SerializedName("account_id") val accountId: String,
    @SerializedName("device_token") val deviceToken: String,
    val gender: String,
    val name: String,
    val password: String,
    @SerializedName("school_number") val schoolNumber: String
)