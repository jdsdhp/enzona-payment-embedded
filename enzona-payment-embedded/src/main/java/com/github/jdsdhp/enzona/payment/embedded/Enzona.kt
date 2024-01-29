package com.github.jdsdhp.enzona.payment.embedded

import com.github.jdsdhp.enzona.payment.embedded.di.DatasourceModule
import com.github.jdsdhp.enzona.payment.embedded.domain.model.CancelStatus
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Item
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Payment
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Token
import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue

/**
 * Interface representing the Enzona SDK.
 */
interface Enzona {

    companion object {

        @Volatile
        private var instance: Enzona? = null // Volatile modifier is necessary

        fun getInstance() =
            instance ?: synchronized(this) { // synchronized to avoid concurrency problem
                instance ?: EnzonaImpl(
                    authRemoteDatasource = DatasourceModule.authRemoteDatasource,
                    paymentRemoteDatasource = DatasourceModule.paymentRemoteDatasource,
                ).also { instance = it }
            }
    }

    /**
     * Enumeration representing different API URLs.
     */
    enum class ApiUrl(val url: String) {
        OFFICIAL(url = "https://api.enzona.net"),
        SANDBOX(url = "https://apisandbox.enzona.net"),
    }

    /**
     * Initializes the Enzona SDK with the specified parameters.
     * @param apiUrl The API URL to be used (official or sandbox).
     * @param merchantConsumerKey The consumer key associated with the merchant.
     * @param merchantConsumerSecret The consumer secret associated with the merchant.
     * @param merchantUUID The UUID of the merchant.
     */
    fun initialize(
        apiUrl: ApiUrl,
        merchantConsumerKey: String,
        merchantConsumerSecret: String,
        merchantUUID: String,
    )

    /**
     * Suspend function to authenticate using Enzona credentials.
     * @return ResultValue containing the authentication token.
     */
    suspend fun authenticate(): ResultValue<Token>

    /**
     * Suspend function to create a payment using Enzona.
     * @param discount The discount amount.
     * @param shipping The shipping amount.
     * @param tip The tip amount.
     * @param buyerIdentityCode The identity code of the buyer.
     * @param cancelUrl The URL to redirect to when the payment is canceled.
     * @param currency The currency of the payment.
     * @param description Description of the payment.
     * @param invoiceNumber The invoice number associated with the payment.
     * @param merchantOpId The operation ID associated with the merchant.
     * @param returnUrl The URL to return to after a successful payment.
     * @param terminalId The terminal ID associated with the payment.
     * @param items List of items included in the payment.
     * @return ResultValue containing the payment information.
     */
    suspend fun createPayment(
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
    ): ResultValue<Payment>

    /**
     * Suspend function to get payment details using transaction UUID.
     * @param transactionUuid The UUID of the transaction.
     * @return ResultValue containing the payment information.
     */
    suspend fun getPaymentDetails(transactionUuid: String): ResultValue<Payment>

    /**
     * Suspend function to cancel a payment using transaction UUID.
     * @param transactionUuid The UUID of the transaction to be canceled.
     * @return ResultValue containing the cancellation status.
     */
    suspend fun cancelPayment(transactionUuid: String): ResultValue<CancelStatus>

    /**
     * Suspend function to complete a payment using transaction UUID.
     * @param transactionUuid The UUID of the transaction to be completed.
     * @return ResultValue containing the payment information after completion.
     */
    suspend fun completePayment(transactionUuid: String): ResultValue<Payment>

}