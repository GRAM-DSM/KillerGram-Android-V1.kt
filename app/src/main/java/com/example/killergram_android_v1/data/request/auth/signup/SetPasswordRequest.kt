package com.example.killergram_android_v1.data.request.auth.signup

import com.google.gson.annotations.SerializedName

data class SetPasswordRequest(
    @SerializedName("account_id") val accountId: String,
    @SerializedName("password") val password: String,
)
