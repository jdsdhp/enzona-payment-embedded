package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
internal data class CreateReceiptCodeDto(
    @SerializedName("amount") val amount: String,
    @SerializedName("cash_advance") val cashAdvance: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("description") val description: String,
    @SerializedName("fingerprint") val fingerprint: String,
    @SerializedName("funding_source_uuid") val fundingSourceUuid: String,
    @SerializedName("payment_password") val paymentPassword: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("vendor_identity_code") val vendorIdentityCode: String
)