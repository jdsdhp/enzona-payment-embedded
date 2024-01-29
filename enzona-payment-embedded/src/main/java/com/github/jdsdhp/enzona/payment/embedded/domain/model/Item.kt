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
 * Data class representing an item.
 * @property quantity The quantity of the item.
 * @property name The name of the item.
 * @property description The description of the item.
 * @property price The price of the item.
 * @property tax The tax associated with the item.
 */
data class Item(
    val quantity: Int,
    val name: String,
    val description: String,
    val price: Double,
    val tax: Double,
)