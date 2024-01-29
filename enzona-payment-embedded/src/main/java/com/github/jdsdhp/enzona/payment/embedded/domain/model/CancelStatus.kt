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

package com.github.jdsdhp.enzona.payment.embedded.domain.model

/**
 * Data class representing the cancellation status of a payment.
 * @property statusCode The code indicating the cancellation status.
 * @property statusName The name describing the cancellation status.
 * @property transactionName The name of the transaction associated with the cancellation.
 * @property transactionUuid The UUID of the transaction associated with the cancellation.
 * @property updateAt The timestamp indicating when the cancellation status was last updated.
 */
data class CancelStatus(
    val statusCode: Int,
    val statusName: String,
    val transactionName: String,
    val transactionUuid: String,
    val updateAt: String,
)