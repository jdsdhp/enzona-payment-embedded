package com.github.jdsdhp.enzona.payment.embedded.domain.model

/**
 * Data class representing the cancellation status of a payment.
 * @property statusCode The code indicating the cancellation status.
 * @property statusName The name describing the cancellation status.
 * @property transactionName The name of the transaction associated with the cancellation.
 * @property transactionUuid The UUID of the transaction associated with the cancellation.
 * @property updateAt The timestamp indicating when the cancellation status was last updated.
 */
data class CancelStatus(
    val statusCode: Int,
    val statusName: String,
    val transactionName: String,
    val transactionUuid: String,
    val updateAt: String,
)