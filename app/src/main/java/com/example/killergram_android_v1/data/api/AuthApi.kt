package com.example.killergram_android_v1.data.api

import com.example.killergram_android_v1.data.request.auth.EmailRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/users/send-verification")
    fun sendEmail(
        @Body request: EmailRequest
    ): Call<Void>


}