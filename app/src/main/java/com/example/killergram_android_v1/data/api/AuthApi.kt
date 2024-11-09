package com.example.killergram_android_v1.data.api

import com.example.killergram_android_v1.data.request.auth.signup.EmailRequest
import com.example.killergram_android_v1.data.request.auth.signup.SetPasswordRequest
import com.example.killergram_android_v1.data.request.auth.signup.VerifyEmailRequest
import com.example.killergram_android_v1.data.response.auth.signup.VerifyEmailResponse
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