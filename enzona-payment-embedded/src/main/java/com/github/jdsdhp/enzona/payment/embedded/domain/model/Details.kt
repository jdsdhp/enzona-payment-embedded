package com.github.jdsdhp.enzona.payment.embedded.domain.model

/**
 * Data class representing details associated with an amount.
 * @property discount The discount amount.
 * @property shipping The shipping amount.
 * @property tax The tax amount.
 * @property tip The tip amount.
 */
data class Details(
    val discount: Double,
    val shipping: Double,
    val tax: Double,
    val tip: Double,
)