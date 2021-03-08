package com.ac.taipeizooguide.ui.district

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ac.taipeizooguide.model.DistrictResult
import com.ac.taipeizooguide.repository.DistrictRepository

/**
 * Created on 2021/3/8.
 */
class DistricViewModel(private val districtRepository: DistrictRepository) : ViewModel() {

    val liveData = MutableLiveData<DistrictResult>()

    suspend fun getDistrictList() {
        liveData.postValue(districtRepository.getDistricts())
    }

}