package com.ac.taipeizooguide.di

import com.ac.taipeizooguide.repository.ApiRepository
import org.koin.dsl.module

/**
 * Created on 2021/3/9.
 */
val repoModule = module {
    single { ApiRepository(get(), get()) }
}

