package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
internal data class ItemDto(
    @SerializedName("description") val description: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Double,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("tax") val tax: Double,
)