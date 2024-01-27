package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
internal data class AmountResponseDto(
    @SerializedName("details") val details: DetailsResponseDto,
    @SerializedName("total") val total: String,
)