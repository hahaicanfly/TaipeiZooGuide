package com.ac.taipeizooguide.ui.district

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ac.taipeizooguide.model.DistrictResult
import com.ac.taipeizooguide.network.Response
import com.ac.taipeizooguide.repository.DistrictRepository
import kotlinx.coroutines.Dispatchers

/**
 * Created on 2021/3/8.
 */
class DistrictViewModel(private val districtRepository: DistrictRepository) : ViewModel() {

    val districtList: LiveData<Response<DistrictResult>> = liveData(Dispatchers.IO) {
        emit(districtRepository.getDistricts())
    }

}