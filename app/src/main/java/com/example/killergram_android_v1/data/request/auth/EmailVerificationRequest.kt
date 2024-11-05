package com.example.killergram_android_v1.data.request.auth

import com.google.gson.annotations.SerializedName

data class EmailVerificationRequest(
    @SerializedName("email") val email: String
)
