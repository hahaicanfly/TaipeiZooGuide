package com.ac.taipeizooguide.network

import com.ac.taipeizooguide.model.DistrictResponse
import com.ac.taipeizooguide.model.DistrictResult
import com.ac.taipeizooguide.model.PlantResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created on 2021/3/8.
 */
interface ApiService {

    @GET("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    suspend fun getDistricts(): DistrictResponse

    @GET("api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire")
    suspend fun getPlants(
        @Query("q") q: String = "",
        @Query("limit") limit: String = "",
        @Query("offset") offset: String = ""
    ): PlantResponse

}