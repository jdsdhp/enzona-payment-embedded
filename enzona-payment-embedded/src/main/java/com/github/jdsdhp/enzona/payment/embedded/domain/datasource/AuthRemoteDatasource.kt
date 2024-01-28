package com.github.jdsdhp.enzona.payment.embedded.domain.datasource

import com.github.jdsdhp.enzona.payment.embedded.Enzona
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Token
import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue

/**
 * Interface representing a remote data source for authentication.
 */
internal interface AuthRemoteDatasource {

    /**
     * Suspend function to authenticate using consumer key and consumer secret.
     * @param apiUrl The Enzona API URL to be used (official or sandbox).
     * @param consumerKey The consumer key for authentication.
     * @param consumerSecret The consumer secret for authentication.
     * @return ResultValue containing the authentication token.
     */
    suspend fun authenticate(
        apiUrl: Enzona.ApiUrl,
        consumerKey: String,
        consumerSecret: String,
    ): ResultValue<Token>

}