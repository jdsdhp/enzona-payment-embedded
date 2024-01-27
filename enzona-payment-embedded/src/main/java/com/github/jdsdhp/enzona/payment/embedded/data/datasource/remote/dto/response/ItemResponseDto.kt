package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
internal data class ItemResponseDto(
    @SerializedName("description") val description: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: String,
    @SerializedName("quantity") val quantity: String,
    @SerializedName("tax") val tax: String,
)