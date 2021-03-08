package com.ac.taipeizooguide.repository
import com.ac.taipeizooguide.model.DistrictResult
import com.ac.taipeizooguide.network.ApiService

/**
 * Created on 2021/3/9.
 */

class DistrictRepository(private val apiService: ApiService) {
    suspend fun getDistricts(): DistrictResult {
        return apiService.getDistricts().result
    }
}