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

package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource

import com.github.jdsdhp.enzona.payment.embedded.Enzona
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource.util.get
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource.util.post
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request.AmountDto
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request.DetailsDto
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request.create.CreatePaymentDto
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.response.PaymentResponseDto
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.response.cancel.CancelResponseDto
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.mapper.asData
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.mapper.asDomain
import com.github.jdsdhp.enzona.payment.embedded.domain.datasource.PaymentRemoteDatasource
import com.github.jdsdhp.enzona.payment.embedded.domain.datasource.RemoteDatasource
import com.github.jdsdhp.enzona.payment.embedded.domain.model.CancelStatus
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Item
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Payment
import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient

internal class PaymentRemoteDatasourceImpl(
    private val okHttpClient: OkHttpClient,
    private val dispatcher: CoroutineDispatcher,
    private val remoteDatasource: RemoteDatasource,
) : PaymentRemoteDatasource {

    /**
     * Enumeration representing different API endpoints.
     */
    private enum class Endpoint(val path: String) {
        PAYMENTS("payment/v1.0.0/payments")
    }

    /**
     * Gson instance for JSON serialization and deserialization.
     */
    private val gson: Gson = Gson()

    override suspend fun createPayment(
        apiUrl: Enzona.ApiUrl,
        token: String,
        discount: Double,
        shipping: Double,
        tip: Double,
        buyerIdentityCode: String,
        cancelUrl: String,
        currency: String,
        description: String,
        invoiceNumber: String,
        merchantOpId: Long,
        merchantUuid: String,
        returnUrl: String,
        terminalId: String,
        items: List<Item>,
    ): ResultValue<Payment> = withContext(dispatcher) {
        remoteDatasource.call {
            var totalPrice = 0.0
            var totalTax = 0.0
            items.forEach {
                totalPrice += it.price * it.quantity
                totalTax += it.tax
            }
            val details = DetailsDto(
                discount = "${discount}0",
                shipping = "${shipping}0",
                tax = "${totalTax}0",
                tip = "${tip}0",
            )
            val amount = AmountDto(
                details = details,
                total = "${totalPrice + (shipping + totalTax + tip - discount)}0",
            )

            val createPayment = CreatePaymentDto(
                amount = amount,
                buyerIdentityCode = buyerIdentityCode,
                cancelUrl = cancelUrl,
                currency = currency,
                description = description,
                invoiceNumber = invoiceNumber,
                items = items.map { it.asData() },
                merchantOpId = merchantOpId.toString().padStart(12, '0'),
                merchantUuid = merchantUuid,
                returnUrl = returnUrl,
                terminalId = terminalId,
            )
            val res = okHttpClient.post(
                fullUrl = "${apiUrl.url}/${Endpoint.PAYMENTS.path}",
                mediaTypeContent = "application/json",
                content = gson.toJson(createPayment),
                headers = mapOf("Authorization" to "Bearer $token"),
            )

            gson.fromJson(res.body?.string(), PaymentResponseDto::class.java).asDomain()
        }
    }

    override suspend fun getPaymentDetails(
        apiUrl: Enzona.ApiUrl,
        token: String,
        transactionUuid: String,
    ): ResultValue<Payment> = withContext(dispatcher) {
        remoteDatasource.call {
            val res = okHttpClient.get(
                fullUrl = "${apiUrl.url}/${Endpoint.PAYMENTS.path}/$transactionUuid",
                headers = mapOf("Authorization" to "Bearer $token"),
            )
            gson.fromJson(res.body?.string(), PaymentResponseDto::class.java).asDomain()
        }
    }

    override suspend fun cancelPayment(
        apiUrl: Enzona.ApiUrl,
        token: String,
        transactionUuid: String,
    ): ResultValue<CancelStatus> = withContext(dispatcher) {
        remoteDatasource.call {
            val res = okHttpClient.post(
                fullUrl = "${apiUrl.url}/${Endpoint.PAYMENTS.path}/$transactionUuid/cancel",
                mediaTypeContent = "application/json",
                content = "",
                headers = mapOf("Authorization" to "Bearer $token"),
            )
            gson.fromJson(res.body?.string(), CancelResponseDto::class.java).asDomain()
        }
    }

    override suspend fun completePayment(
        apiUrl: Enzona.ApiUrl,
        token: String,
        transactionUuid: String,
    ): ResultValue<Payment> = withContext(dispatcher) {
        remoteDatasource.call {
            val res = okHttpClient.post(
                fullUrl = "${apiUrl.url}/${Endpoint.PAYMENTS.path}/$transactionUuid/complete",
                mediaTypeContent = "application/json",
                content = "",
                headers = mapOf("Authorization" to "Bearer $token"),
            )
            gson.fromJson(res.body?.string(), PaymentResponseDto::class.java).asDomain()
        }
    }

}