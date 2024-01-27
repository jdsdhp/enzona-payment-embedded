package com.github.jdsdhp.enzona.payment.embedded.domain.model

/**
 * Data class representing an amount, including details and total.
 * @property details The details associated with the amount.
 * @property total The total amount.
 */
internal data class Amount(
    val details: Details,
    val total: Double,
)