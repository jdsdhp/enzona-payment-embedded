package com.github.jdsdhp.enzona.payment.embedded.domain.datasource

import com.github.jdsdhp.enzona.payment.embedded.domain.model.Payment
import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue

/**
 * Interface representing a remote data source for payment creation.
 */
internal interface PaymentRemoteDatasource {

    /**
     * Suspend function to create a payment remotely.
     * @return ResultValue containing the payment data.
     */
    suspend fun createPayment(): ResultValue<Payment>

}