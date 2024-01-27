package com.github.jdsdhp.enzona.payment.embedded

import com.github.jdsdhp.enzona.payment.embedded.domain.datasource.AuthRemoteDatasource
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Payment
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Token
import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue
import javax.inject.Inject

internal class EnzonaImpl @Inject constructor(
    private val authRemoteDatasource: AuthRemoteDatasource
) : Enzona {

    private lateinit var apiUrl: Enzona.ApiUrl
    private lateinit var consumerKey: String
    private lateinit var consumerSecret: String
    private lateinit var merchantUUID: String

    override fun initialize(
        apiUrl: Enzona.ApiUrl,
        merchantConsumerKey: String,
        merchantConsumerSecret: String,
        merchantUUID: String,
        //autoRefreshToken: Boolean, //TODO: Pending for implementation.
        //scopes: List<String>, //TODO: Pending for implementation.
    ) {
        this.apiUrl = apiUrl
        this.consumerKey = merchantConsumerKey
        this.consumerSecret = merchantConsumerSecret
        this.merchantUUID = merchantUUID
    }

    override suspend fun authenticate(): ResultValue<Token> =
        authRemoteDatasource.authenticate(
            consumerKey = consumerKey,
            consumerSecret = consumerSecret,
        )

    override suspend fun createPayment(): ResultValue<Payment> {
        TODO("Not yet implemented")
    }

}
