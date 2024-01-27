package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
internal data class DetailsResponseDto(
    @SerializedName("discount") val discount: Double,
    @SerializedName("shipping") val shipping: Double,
    @SerializedName("tax") val tax: Double,
    @SerializedName("tip") val tip: Double,
)