package com.github.jdsdhp.enzona.payment.embedded.domain.model

/**
 * Enumeration representing different scopes.
 * @property label The label associated with the scope.
 */
internal enum class Scope(val label: String) {

    /**
     * Scope for Enzona business payment.
     */
    ENZONA_BUSINESS_PAYMENT("enzona_business_payment")

}
