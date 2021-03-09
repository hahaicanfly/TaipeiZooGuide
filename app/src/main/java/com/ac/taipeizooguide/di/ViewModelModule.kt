package com.ac.taipeizooguide.di

import com.ac.taipeizooguide.ui.district.DistrictViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created on 2021/3/8.
 */
val viewModelModule = module {
    viewModel { DistrictViewModel(get()) }
}