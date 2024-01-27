package com.github.jdsdhp.enzona.payment.embedded.domain.model

/**
 * Data class representing a link.
 * @property rel The relationship type of the link.
 * @property method The HTTP method associated with the link.
 * @property href The URL associated with the link.
 */
data class Link(
    val rel: String,
    val method: String,
    val href: String,
)