package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
internal data class AmountDto(
    @SerializedName("details") val details: DetailsDto,
    @SerializedName("total") val total: Double,
)