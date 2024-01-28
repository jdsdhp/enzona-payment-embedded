package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.mapper

import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request.AmountDto
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request.DetailsDto
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.request.ItemDto
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.response.ItemResponseDto
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.response.LinkResponseDto
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.response.cancel.CancelResponseDto
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.dto.response.PaymentResponseDto
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Amount
import com.github.jdsdhp.enzona.payment.embedded.domain.model.CancelStatus
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Details
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Item
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Link
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Payment

internal fun DetailsDto.asDomain(): Details =
    Details(
        discount = discount.toDouble(),
        shipping = shipping.toDouble(),
        tax = tax.toDouble(),
        tip = tip.toDouble(),
    )

internal fun Details.asData(): DetailsDto =
    DetailsDto(
        discount = "${discount}0",
        shipping = "${shipping}0",
        tax = "${tax}0",
        tip = "${tip}0",
    )

internal fun AmountDto.asDomain(): Amount =
    Amount(details = details.asDomain(), total = total.toDouble())

internal fun Amount.asData(): AmountDto =
    AmountDto(details = details.asData(), total = "${total}0")

internal fun ItemDto.asDomain(): Item =
    Item(
        quantity = quantity,
        name = name,
        description = description,
        price = price.toDouble(),
        tax = tax.toDouble(),
    )

internal fun Item.asData(): ItemDto =
    ItemDto(
        quantity = quantity,
        name = name,
        description = description,
        price = "${price}0",
        tax = "${tax}0",
    )

internal fun ItemResponseDto.asDomain(): Item =
    Item(
        quantity = quantity.toInt(),
        name = name,
        description = description,
        price = price.toDouble(),
        tax = tax.toDouble(),
    )

internal fun LinkResponseDto.asDomain(): Link =
    Link(rel = rel, method = method, href = href)

internal fun PaymentResponseDto.asDomain() = Payment(
    transactionUuid,
    createdAt,
    updateAt,
    statusCode,
    statusName,
    currency,
    description,
    totalPrice = amount.total.toDouble(),
    items = items.map { it.asDomain() },
    links = links.map { it.asDomain() }
)

internal fun CancelResponseDto.asDomain(): CancelStatus = CancelStatus(
    statusCode = statusCode.toInt(),
    statusName = statusName,
    transactionName = transactionName,
    transactionUuid = transactionUuid,
    updateAt = updateAt,
)