package com.github.jdsdhp.enzona.payment.embedded.domain.model

/**
 * Data class representing an item.
 * @property quantity The quantity of the item.
 * @property name The name of the item.
 * @property description The description of the item.
 * @property price The price of the item.
 * @property tax The tax associated with the item.
 */
data class Item(
    val quantity: Int,
    val name: String,
    val description: String,
    val price: Double,
    val tax: Double,
)