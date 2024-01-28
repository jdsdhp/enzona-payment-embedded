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