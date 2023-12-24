package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
internal data class PayDto(
    @SerializedName("amount") val amount: AmountDto,
    @SerializedName("cart_id") val cartId: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("description") val description: String,
    @SerializedName("fingerprint") val fingerprint: String,
    @SerializedName("funding_source_uuid") val fundingSourceUuid: String,
    @SerializedName("id_shop") val idShop: String,
    @SerializedName("items") val items: List<PayItemDto>,
    @SerializedName("merchant_uuid") val merchantUuid: String,
    @SerializedName("payment_password") val paymentPassword: String
)

@Keep
data class PayItemDto(
    @SerializedName("price") val price: Double,
    @SerializedName("product_id") val productId: String,
    @SerializedName("product_name") val productName: String,
    @SerializedName("quantity") val quantity: Int
)