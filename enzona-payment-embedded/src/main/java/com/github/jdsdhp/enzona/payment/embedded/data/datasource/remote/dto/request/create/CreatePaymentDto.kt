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

package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request.create

import androidx.annotation.Keep
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request.AmountDto
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request.ItemDto
import com.google.gson.annotations.SerializedName

@Keep
internal data class CreatePaymentDto(
    @SerializedName("amount") val amount: AmountDto,
    @SerializedName("buyer_identity_code") val buyerIdentityCode: String,
    @SerializedName("cancel_url") val cancelUrl: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("description") val description: String,
    @SerializedName("invoice_number") val invoiceNumber: String,
    @SerializedName("items") val items: List<ItemDto>,
    @SerializedName("merchant_op_id") val merchantOpId: String,
    @SerializedName("merchant_uuid") val merchantUuid: String,
    @SerializedName("return_url") val returnUrl: String,
    @SerializedName("terminal_id") val terminalId: String,
)