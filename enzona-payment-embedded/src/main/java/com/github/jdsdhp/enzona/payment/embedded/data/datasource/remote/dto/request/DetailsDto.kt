package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
internal data class DetailsDto(
    @SerializedName("discount") val discount: Double,
    @SerializedName("shipping") val shipping: Double,
    @SerializedName("tax") val tax: Double,
    @SerializedName("tip") val tip: Double,
)