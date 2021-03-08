package com.ac.taipeizooguide

import android.app.Application
import com.ac.taipeizooguide.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created on 2021/3/8.
 */
open class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(viewModelModule))
        }
    }
}