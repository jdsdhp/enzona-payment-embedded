/*
 * Copyright (c) 2024 jesusd0897.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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