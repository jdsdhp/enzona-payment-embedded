package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
internal data class ConfirmPaymentDto(
    @SerializedName("fingerprint") val fingerprint: String,
    @SerializedName("funding_source_uuid") val fundingSourceUuid: String,
    @SerializedName("payment_password") val paymentPassword: String,
)