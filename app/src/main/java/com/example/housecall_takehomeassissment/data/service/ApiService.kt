package com.example.housecall_takehomeassissment.data.service

import com.example.housecall_takehomeassissment.data.models.DrugResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("drugs.json")
    suspend fun getDrugs(
        @Query("name") name: String,
        @Query("expand") expand: String = "PSN"
    ): DrugResponse
}