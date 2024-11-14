package com.example.killergram_android_v1.data.api

import com.example.killergram_android_v1.data.response.sport.GetSportResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface SportApi {
    @GET("/sports/getSport")
    fun getSport(
        @Header("Authorization") accessToken: String
    ): Call<List<GetSportResponse>>
}