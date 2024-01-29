package com.github.jdsdhp.enzona.payment.embedded.di

import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource.util.ignoreCertificates
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

internal object NetworkModule {

    private const val TIME_OUT_CONNECT = 20L
    private const val TIME_OUT_WRITE = 20L
    private const val TIME_OUT_READ = 20L

    internal val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT_WRITE, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
        .ignoreCertificates()
        .addInterceptor(HttpLoggingInterceptor { message -> println("LOG-APP: $message") }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addNetworkInterceptor(HttpLoggingInterceptor { message -> println("LOG-NET: $message") }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

}