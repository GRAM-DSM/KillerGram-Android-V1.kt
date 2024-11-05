package com.example.killergram_android_v1.data.api

import com.example.killergram_android_v1.data.request.auth.EmailVerificationRequest
import com.example.killergram_android_v1.data.response.auth.EmailVerificationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/send-verification")
    fun emailVerification(
        @Body request: EmailVerificationRequest
    ): Call<EmailVerificationResponse>
}