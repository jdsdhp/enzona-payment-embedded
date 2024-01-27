package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource

import com.github.jdsdhp.enzona.payment.embedded.domain.datasource.RemoteDatasource
import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue
import javax.inject.Inject

/**
 * Internal class representing a remote data source abstraction.
 */
internal class RemoteDatasourceImpl @Inject constructor() : RemoteDatasource {

    override suspend fun <T> call(request: suspend () -> T): ResultValue<T> =
        safeApiCall(request = request)

    /**
     * Performs a safe API call without token handling, handling exceptions and returning a ResultValue.
     * @param request The suspended function performing the API call.
     * @return A [ResultValue] containing the API call result.
     */
    private suspend fun <T> safeApiCall(request: suspend () -> T): ResultValue<T> =
        try {
            val res = request.invoke()
            ResultValue.Success(res)
        } catch (e: Exception) {
            ResultValue.Error(e)
        }

}