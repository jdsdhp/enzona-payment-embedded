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
