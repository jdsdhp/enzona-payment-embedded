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

package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.response.cancel

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
internal data class CancelResponseDto(
    @SerializedName("status_code") val statusCode: String,
    @SerializedName("status_denom") val statusName: String,
    @SerializedName("transaction_denom") val transactionName: String,
    @SerializedName("transaction_uuid") val transactionUuid: String,
    @SerializedName("updated_at") val updateAt: String,
)