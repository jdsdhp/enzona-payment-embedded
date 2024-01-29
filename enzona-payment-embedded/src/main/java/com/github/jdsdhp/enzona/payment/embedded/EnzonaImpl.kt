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

package com.github.jdsdhp.enzona.payment.embedded

import com.github.jdsdhp.enzona.payment.embedded.domain.datasource.AuthRemoteDatasource
import com.github.jdsdhp.enzona.payment.embedded.domain.datasource.PaymentRemoteDatasource
import com.github.jdsdhp.enzona.payment.embedded.domain.model.CancelStatus
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Item
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Payment
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Token
import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue

internal class EnzonaImpl(
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
            apiUrl = apiUrl,
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
        apiUrl = apiUrl,
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

    override suspend fun getPaymentDetails(transactionUuid: String): ResultValue<Payment> =
        paymentRemoteDatasource.getPaymentDetails(
            apiUrl = apiUrl,
            token = token?.accessToken ?: "",
            transactionUuid = transactionUuid,
        )

    override suspend fun cancelPayment(transactionUuid: String): ResultValue<CancelStatus> =
        paymentRemoteDatasource.cancelPayment(
            apiUrl = apiUrl,
            token = token?.accessToken ?: "",
            transactionUuid = transactionUuid,
        )

    override suspend fun completePayment(transactionUuid: String): ResultValue<Payment> =
        paymentRemoteDatasource.completePayment(
            apiUrl = apiUrl,
            token = token?.accessToken ?: "",
            transactionUuid = transactionUuid,
        )

}
