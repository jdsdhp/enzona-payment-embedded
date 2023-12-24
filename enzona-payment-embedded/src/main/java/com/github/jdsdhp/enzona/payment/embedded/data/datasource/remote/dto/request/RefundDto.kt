package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
internal data class RefundDto(
    @SerializedName("amount") val amount: RefundAmountDto,
    @SerializedName("commerce_refund_id") val commerceRefundId: String,
    @SerializedName("description") val description: String,
    @SerializedName("username") val username: String
)

@Keep
internal data class RefundAmountDto(
    @SerializedName("total") val total: String
)