package com.example.killergram_android_v1.data.request.auth.signup

import com.google.gson.annotations.SerializedName

data class VerifyEmailRequest(
    @SerializedName("email") val email: String,
    @SerializedName("code") val emailCode: String,
)
