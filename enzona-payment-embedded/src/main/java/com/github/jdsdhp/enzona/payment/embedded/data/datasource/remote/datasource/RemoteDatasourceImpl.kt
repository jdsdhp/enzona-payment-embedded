/*
 * Copyright (c) 2024 jesusd0897.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource

import com.github.jdsdhp.enzona.payment.embedded.domain.datasource.RemoteDatasource
import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue

/**
 * Internal class representing a remote data source abstraction.
 */
internal class RemoteDatasourceImpl : RemoteDatasource {

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