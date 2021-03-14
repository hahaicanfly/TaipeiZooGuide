package com.ac.taipeizooguide.repository

import com.ac.taipeizooguide.model.DistrictResult
import com.ac.taipeizooguide.model.PlantResult
import com.ac.taipeizooguide.network.ApiService
import com.ac.taipeizooguide.network.Response
import com.ac.taipeizooguide.network.ResponseHandler

/**
 * Created on 2021/3/9.
 */

class ApiRepository(
    private val apiService: ApiService,
    private val responseHandler: ResponseHandler
) {
    suspend fun getDistricts(): Response<DistrictResult> {
        return try {
            val resp = apiService.getDistricts().result
            responseHandler.handleSuccess(resp)
        } catch (e: Exception) {
            responseHandler.handleError(e)
        }
    }

    suspend fun getPlants(location: String?): Response<PlantResult> {
        return try {
            val resp = apiService.getPlants(q = location ?: "").result
            responseHandler.handleSuccess(resp)
        } catch (e: Exception) {
            responseHandler.handleError(e)
        }
    }
}