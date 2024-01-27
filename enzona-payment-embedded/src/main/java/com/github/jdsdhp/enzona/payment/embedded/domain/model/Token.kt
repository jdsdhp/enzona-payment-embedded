package com.github.jdsdhp.enzona.payment.embedded.domain.model

/**
 * Data class representing an authentication token.
 * @property accessToken The access token for authentication.
 * @property expireTime The expiration time of the access token.
 */
data class Token(
    val accessToken: String,
    val expireTime: Long,
)