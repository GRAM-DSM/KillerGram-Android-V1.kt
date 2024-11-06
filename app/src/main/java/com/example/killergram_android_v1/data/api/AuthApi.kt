package com.example.killergram_android_v1.data.api

import com.example.killergram_android_v1.data.request.auth.EmailRequest
import com.example.killergram_android_v1.data.request.auth.SetPasswordRequest
import com.example.killergram_android_v1.data.request.auth.VerifyEmailRequest
import com.example.killergram_android_v1.data.response.auth.VerifyEmailResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApi {
    @POST("/users/send-verification")
    fun sendEmail(
        @Body request: EmailRequest
    ): Call<Void>

    @POST("/users/verify-email")
    fun verifyEmail(
        @Body request: VerifyEmailRequest
    ): Call<VerifyEmailResponse>

    @PATCH("users/reset-password")
    fun setPassword(
        @Body request: SetPasswordRequest
    ): Call<Void>
}