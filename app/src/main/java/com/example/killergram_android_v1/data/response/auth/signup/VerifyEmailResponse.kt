package com.example.killergram_android_v1.data.response.auth.signup

import com.google.gson.annotations.SerializedName

data class VerifyEmailResponse(
    @SerializedName("email") val email: String,
)
