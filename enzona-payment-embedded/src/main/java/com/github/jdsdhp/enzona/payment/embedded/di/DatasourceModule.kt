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

import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource.AuthRemoteDatasourceImpl
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource.PaymentRemoteDatasourceImpl
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource.RemoteDatasourceImpl
import com.github.jdsdhp.enzona.payment.embedded.di.NetworkModule.okHttpClient
import kotlinx.coroutines.Dispatchers

internal object DatasourceModule {

    private val remoteDatasource by lazy { RemoteDatasourceImpl() }

    internal val authRemoteDatasource by lazy {
        AuthRemoteDatasourceImpl(
            okHttpClient = okHttpClient,
            dispatcher = Dispatchers.IO,
            remoteDatasource = remoteDatasource,
        )
    }

    internal val paymentRemoteDatasource by lazy {
        PaymentRemoteDatasourceImpl(
            okHttpClient = okHttpClient,
            dispatcher = Dispatchers.IO,
            remoteDatasource = remoteDatasource,
        )
    }

}