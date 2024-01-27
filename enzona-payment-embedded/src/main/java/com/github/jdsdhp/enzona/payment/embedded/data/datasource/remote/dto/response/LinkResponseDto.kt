package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
internal data class LinkResponseDto(
    @SerializedName("href") val href: String,
    @SerializedName("method") val method: String,
    @SerializedName("rel") val rel: String,
)