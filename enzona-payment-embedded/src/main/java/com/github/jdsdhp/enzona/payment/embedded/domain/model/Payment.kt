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
 * Data class representing a payment.
 * @property transactionUuid The unique identifier for the payment transaction.
 * @property createdAt The timestamp indicating when the payment was created.
 * @property updatedAt The timestamp indicating when the payment was last updated.
 * @property statusCode The numeric status code of the payment.
 * @property statusName The name of the payment status.
 * @property currency The currency used for the payment.
 * @property description A description associated with the payment.
 * @property totalPrice The total price of the payment.
 * @property items The list of items included in the payment.
 * @property links The list of links associated with the payment.
 */
data class Payment(
    val transactionUuid: String,
    val createdAt: String,
    val updatedAt: String,
    val statusCode: Int,
    val statusName: String,
    val currency: String,
    val description: String,
    val totalPrice: Double,
    val items: List<Item>,
    val links: List<Link>,
)