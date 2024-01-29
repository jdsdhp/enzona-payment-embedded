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
