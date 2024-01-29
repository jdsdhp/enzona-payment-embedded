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