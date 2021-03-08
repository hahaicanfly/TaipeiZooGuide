package com.ac.taipeizooguide.di

import com.ac.taipeizooguide.repository.DistrictRepository
import org.koin.dsl.module

/**
 * Created on 2021/3/9.
 */
val repoModule = module {
    factory { DistrictRepository(get()) }
}

