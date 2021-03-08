package com.ac.taipeizooguide.ui.district

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created on 2021/3/8.
 */
class DistricViewModel : ViewModel() {

    private val _districName = MutableLiveData<String>().apply {
        value = "Distric A"
    }
    val districName: LiveData<String> = _districName
}