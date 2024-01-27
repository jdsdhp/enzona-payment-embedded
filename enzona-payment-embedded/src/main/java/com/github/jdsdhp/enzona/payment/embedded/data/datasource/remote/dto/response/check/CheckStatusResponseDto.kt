package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.response.check

import androidx.annotation.Keep
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.response.AmountResponseDto
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.response.ItemResponseDto
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.response.LinkResponseDto
import com.google.gson.annotations.SerializedName

@Keep
internal data class CheckStatusResponseDto(
    @SerializedName("amount") val amount: AmountResponseDto,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("description") val description: String,
    @SerializedName("invoice_number") val invoiceNumber: String,
    @SerializedName("items") val items: List<ItemResponseDto>,
    @SerializedName("links") val links: List<LinkResponseDto>,
    @SerializedName("merchant_op_id") val merchantOpId: String,
    @SerializedName("merchant_uuid") val merchantUuid: String,
    @SerializedName("status_code") val statusCode: String,
    @SerializedName("terminal_id") val terminalId: String,
    @SerializedName("transaction_uuid") val transactionUuid: String,
    @SerializedName("update_at") val updateAt: String,
)