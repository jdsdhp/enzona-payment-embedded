package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
internal data class DetailsDto(
    @SerializedName("discount") val discount: String,
    @SerializedName("shipping") val shipping: String,
    @SerializedName("tax") val tax: String,
    @SerializedName("tip") val tip: String,
)