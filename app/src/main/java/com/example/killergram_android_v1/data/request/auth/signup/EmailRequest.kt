package com.example.killergram_android_v1.data.request.auth.signup

import com.google.gson.annotations.SerializedName

data class EmailRequest(
    @SerializedName("email") val email: String
)
