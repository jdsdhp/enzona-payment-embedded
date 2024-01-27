package com.github.jdsdhp.enzona.payment.embedded

import com.github.jdsdhp.enzona.payment.embedded.domain.model.Payment
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Token
import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue

/**
 * Interface representing the Enzona SDK.
 */
interface Enzona {

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
     * @return ResultValue containing the payment information.
     */
    suspend fun createPayment(): ResultValue<Payment>

}