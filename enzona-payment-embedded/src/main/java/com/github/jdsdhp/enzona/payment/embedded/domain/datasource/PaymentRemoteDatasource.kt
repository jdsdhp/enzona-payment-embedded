package com.github.jdsdhp.enzona.payment.embedded.domain.datasource

import com.github.jdsdhp.enzona.payment.embedded.domain.model.Item
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Payment
import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue

/**
 * Interface representing a remote data source for payment creation.
 */
internal interface PaymentRemoteDatasource {

    /**
     * Suspend function to create a payment remotely.
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

}