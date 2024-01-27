package com.github.jdsdhp.enzona.payment.embedded.domain.datasource

import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue

/**
 * Interface representing a remote data source for making network calls.
 */
internal interface RemoteDatasource {

    /**
     * Executes a network call.
     * @param request The suspending lambda function that defines the network call.
     * @return A [ResultValue] containing the result of the network call.
     */
    suspend fun <T> call(request: suspend () -> T): ResultValue<T>

}
