package com.github.jdsdhp.enzona.payment.embedded.di

import com.github.jdsdhp.enzona.payment.embedded.Enzona
import com.github.jdsdhp.enzona.payment.embedded.EnzonaImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface EnzonaModule {

    @Singleton
    @Binds
    fun bindsEnzona(impl: EnzonaImpl): Enzona

}