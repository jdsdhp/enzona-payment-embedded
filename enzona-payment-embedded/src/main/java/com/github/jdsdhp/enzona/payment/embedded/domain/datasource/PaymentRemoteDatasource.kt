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

package com.github.jdsdhp.enzona.payment.embedded.domain.datasource

import com.github.jdsdhp.enzona.payment.embedded.Enzona
import com.github.jdsdhp.enzona.payment.embedded.domain.model.CancelStatus
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Item
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Payment
import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue

/**
 * Interface representing a remote data source for payment creation.
 */
internal interface PaymentRemoteDatasource {

    /**
     * Suspend function to create a payment remotely.
     * @param apiUrl The Enzona API URL to be used (official or sandbox).
     * @param token The authentication token.
     * @param discount The discount amount for the payment.
     * @param shipping The shipping cost for the payment.
     * @param tip The tip amount for the payment.
     * @param buyerIdentityCode The identity code of the buyer.
     * @param cancelUrl The URL to redirect to when the payment is canceled.
     * @param currency The currency of the payment.
     * @param description The description of the payment.
     * @param invoiceNumber The invoice number associated with the payment.
     * @param merchantOpId The merchant operation ID.
     * @param merchantUuid The UUID of the merchant.
     * @param returnUrl The URL to return to after a successful payment.
     * @param terminalId The terminal ID associated with the payment.
     * @param items List of items included in the payment.
     * @return ResultValue containing the created payment information.
     */
    suspend fun createPayment(
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
    ): ResultValue<Payment>

    /**
     * Suspend function to get payment details remotely.
     * @param apiUrl The Enzona API URL to be used (official or sandbox).
     * @param token The authentication token.
     * @param transactionUuid The UUID of the transaction.
     * @return ResultValue containing the payment details.
     */
    suspend fun getPaymentDetails(
        apiUrl: Enzona.ApiUrl,
        token: String,
        transactionUuid: String
    ): ResultValue<Payment>

    /**
     * Suspend function to cancel a payment remotely.
     * @param apiUrl The Enzona API URL to be used (official or sandbox).
     * @param token The authentication token.
     * @param transactionUuid The UUID of the transaction to be canceled.
     * @return ResultValue containing the cancellation status.
     */
    suspend fun cancelPayment(
        apiUrl: Enzona.ApiUrl,
        token: String,
        transactionUuid: String
    ): ResultValue<CancelStatus>

    /**
     * Suspend function to complete a payment remotely.
     * @param apiUrl The Enzona API URL to be used (official or sandbox).
     * @param token The authentication token.
     * @param transactionUuid The UUID of the transaction to be completed.
     * @return ResultValue containing the payment details after completion.
     */
    suspend fun completePayment(
        apiUrl: Enzona.ApiUrl,
        token: String,
        transactionUuid: String
    ): ResultValue<Payment>

}