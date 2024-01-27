package com.github.jdsdhp.enzona.payment.embedded.util

/**
 * Sealed class representing the result of an operation that can either be successful or an error.
 */
sealed class ResultValue<out T : Any?> {

    /**
     * Represents a successful result with associated data.
     * @param data The data associated with the successful result.
     */
    data class Success<out T>(val data: T) : ResultValue<T>()

    /**
     * Represents an error result with an associated exception.
     * @param exception The exception associated with the error result.
     */
    data class Error(val exception: Exception) : ResultValue<Nothing>()

}
