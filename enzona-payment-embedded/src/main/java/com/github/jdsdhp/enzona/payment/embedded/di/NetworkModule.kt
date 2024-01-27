package com.github.jdsdhp.enzona.payment.embedded.di

import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource.util.ignoreCertificates
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val TIME_OUT_CONNECT = 20L
    private const val TIME_OUT_WRITE = 20L
    private const val TIME_OUT_READ = 20L

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT_WRITE, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
        .ignoreCertificates()
        .build()

}