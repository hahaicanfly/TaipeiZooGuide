package com.ac.taipeizooguide.ui.district

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ac.taipeizooguide.model.District
import com.ac.taipeizooguide.model.DistrictResult
import com.ac.taipeizooguide.model.PlantResult
import com.ac.taipeizooguide.network.Response
import com.ac.taipeizooguide.repository.ApiRepository
import kotlinx.coroutines.Dispatchers

/**
 * Created on 2021/3/8.
 */
class ZooViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    val districtResult: LiveData<Response<DistrictResult>> = liveData(Dispatchers.IO) {
        emit(apiRepository.getDistricts())
    }

    fun getDistrict(position: Int): District? {
        return districtResult.value?.data?.districtList?.get(position)
    }

    fun getPlantList(location: String?): LiveData<Response<PlantResult>> =
        liveData(Dispatchers.IO) {
            emit(apiRepository.getPlants(location))
        }

}