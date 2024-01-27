package com.github.jdsdhp.enzona.payment.embedded.di

import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource.AuthRemoteDatasourceImpl
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource.PaymentRemoteDatasourceImpl
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource.RemoteDatasourceImpl
import com.github.jdsdhp.enzona.payment.embedded.domain.datasource.AuthRemoteDatasource
import com.github.jdsdhp.enzona.payment.embedded.domain.datasource.PaymentRemoteDatasource
import com.github.jdsdhp.enzona.payment.embedded.domain.datasource.RemoteDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DatasourceModule {

    @Singleton
    @Binds
    fun bindsRemoteDatasource(impl: RemoteDatasourceImpl): RemoteDatasource

    @Singleton
    @Binds
    fun bindsAuthRemoteDatasource(impl: AuthRemoteDatasourceImpl): AuthRemoteDatasource

    @Singleton
    @Binds
    fun bindsPaymentRemoteDatasource(impl: PaymentRemoteDatasourceImpl): PaymentRemoteDatasource

}