package com.example.github.common.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofit by lazy { createRetrofit() }

private const val BASE_URL: String = "https://api.github.com/"

private fun createRetrofit(): Retrofit {

    val httpLoggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor {
            Log.d("HttpLogging", it)
        }.apply { level = HttpLoggingInterceptor.Level.BODY }

    val okHttpClient = OkHttpClient.Builder()
        .apply {
            addInterceptor(httpLoggingInterceptor)
            connectTimeout(30, TimeUnit.MINUTES) // 연결 시간 설정. 기본 10 초. 설정 가능 범위 0 ~ Int.MAX_VALUE.
            writeTimeout(30, TimeUnit.MINUTES) // 쓰기 시간 설정. 기본 10 초. 설정 가능 범위 0 ~ Int.MAX_VALUE.
            readTimeout(30, TimeUnit.MINUTES) // 읽기 시간 설정. 기본 10 초. 설정 가능 범위 0 ~ Int.MAX_VALUE.
        }.build()

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}