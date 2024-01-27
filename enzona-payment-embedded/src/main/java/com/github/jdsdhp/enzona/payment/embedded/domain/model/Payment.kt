package com.github.jdsdhp.enzona.payment.embedded.domain.model

/**
 * Data class representing a payment.
 * @property transactionUuid The unique identifier for the payment transaction.
 * @property createdAt The timestamp indicating when the payment was created.
 * @property updatedAt The timestamp indicating when the payment was last updated.
 * @property statusCode The numeric status code of the payment.
 * @property statusName The name of the payment status.
 * @property currency The currency used for the payment.
 * @property description A description associated with the payment.
 * @property totalPrice The total price of the payment.
 * @property items The list of items included in the payment.
 * @property links The list of links associated with the payment.
 */
data class Payment(
    val transactionUuid: String,
    val createdAt: String,
    val updatedAt: String,
    val statusCode: Int,
    val statusName: String,
    val currency: String,
    val description: String,
    val totalPrice: Double,
    val items: List<Item>,
    val links: List<Link>,
)