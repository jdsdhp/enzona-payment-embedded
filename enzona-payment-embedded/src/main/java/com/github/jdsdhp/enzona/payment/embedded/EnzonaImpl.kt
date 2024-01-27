package com.github.jdsdhp.enzona.payment.embedded

import com.github.jdsdhp.enzona.payment.embedded.domain.datasource.AuthRemoteDatasource
import com.github.jdsdhp.enzona.payment.embedded.domain.datasource.PaymentRemoteDatasource
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Item
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Payment
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Token
import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue
import javax.inject.Inject

internal class EnzonaImpl @Inject constructor(
    private val authRemoteDatasource: AuthRemoteDatasource,
    private val paymentRemoteDatasource: PaymentRemoteDatasource,
) : Enzona {

    private lateinit var apiUrl: Enzona.ApiUrl
    private lateinit var consumerKey: String
    private lateinit var consumerSecret: String
    private lateinit var merchantUUID: String

    private var token: Token? = null

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
        ).also {
            if (it is ResultValue.Success) {
                token = it.data
            }
        }

    override suspend fun createPayment(
        discount: Double,
        shipping: Double,
        tip: Double,
        buyerIdentityCode: String,
        cancelUrl: String,
        currency: String,
        description: String,
        invoiceNumber: String,
        merchantOpId: Long,
        returnUrl: String,
        terminalId: String,
        items: List<Item>,
    ): ResultValue<Payment> = paymentRemoteDatasource.createPayment(
        token = token?.accessToken ?: "",
        discount = discount,
        shipping = shipping,
        tip = tip,
        buyerIdentityCode = buyerIdentityCode,
        cancelUrl = cancelUrl,
        currency = currency,
        description = description,
        invoiceNumber = invoiceNumber,
        items = items,
        merchantOpId = merchantOpId,
        merchantUuid = merchantUUID,
        returnUrl = returnUrl,
        terminalId = terminalId,
    )

}
