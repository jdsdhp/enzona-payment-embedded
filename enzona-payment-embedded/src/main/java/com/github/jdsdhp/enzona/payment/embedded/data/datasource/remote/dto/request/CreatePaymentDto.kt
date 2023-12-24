package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
internal data class CreatePaymentDto(
    @SerializedName("amount") val amount: AmountDto,
    @SerializedName("buyer_identity_code") val buyerIdentityCode: String,
    @SerializedName("cancel_url") val cancelUrl: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("description") val description: String,
    @SerializedName("invoice_number") val invoiceNumber: Int,
    @SerializedName("items") val items: List<CreatePaymentItemDto>,
    @SerializedName("merchant_op_id") val merchantOpId: Long,
    @SerializedName("merchant_uuid") val merchantUuid: String,
    @SerializedName("return_url") val returnUrl: String,
    @SerializedName("terminal_id") val terminalId: Int,
)

@Keep
internal data class DetailsDto(
    @SerializedName("discount") val discount: Double,
    @SerializedName("shipping") val shipping: Double,
    @SerializedName("tax") val tax: Double,
    @SerializedName("tip") val tip: Double,
)

@Keep
internal data class CreatePaymentItemDto(
    @SerializedName("description") val description: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Double,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("tax") val tax: Double,
)