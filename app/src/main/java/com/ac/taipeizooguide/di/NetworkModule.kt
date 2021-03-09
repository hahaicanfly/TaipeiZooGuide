package com.ac.taipeizooguide.di

import com.ac.taipeizooguide.BuildConfig
import com.ac.taipeizooguide.network.ApiService
import com.ac.taipeizooguide.network.ResponseHandler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created on 2021/3/8.
 */
const val TIMEOUT_SEC = 15L

val networkModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()).create(ApiService::class.java) }
    single { ResponseHandler() }
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
    .readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
    .callTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
    .retryOnConnectionFailure(true)
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()

