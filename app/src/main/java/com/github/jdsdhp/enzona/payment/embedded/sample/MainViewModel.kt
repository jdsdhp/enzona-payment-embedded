package com.github.jdsdhp.enzona.payment.embedded.sample

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.jdsdhp.enzona.payment.embedded.Enzona
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Item
import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val enzona: Enzona) : ViewModel() {

    data class UiState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val textMessage: String? = null,
        val consumerKey: String = "",
        val consumerSecret: String = "",
        val merchantUUID: String = "",
        val createPayment: CreatePayment = CreatePayment(),
        val items: List<Item> = emptyList(),
    )

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    data class CreatePayment(
        val discount: Double = 0.0,
        val shipping: Double = 0.0,
        val tip: Double = 0.0,
        val buyerIdentityCode: String = "",
        val cancelUrl: String = "https://www.enzona.net/payment/cancel",
        val returnUrl: String = "https://www.enzona.net/payment/return",
        val currency: String = "CUP",
        val description: String = "",
        val invoiceNumber: String = "",
        val merchantOpId: String = "1",
        val terminalId: String = "",
    )

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    items = listOf(
                        Item(
                            quantity = 2,
                            name = "Mango",
                            description = "Product One Description",
                            price = 5.0,
                            tax = 1.0,
                        ),
                        Item(
                            quantity = 3,
                            name = "Orange",
                            description = "Product Two Description",
                            price = 2.0,
                            tax = 2.0,
                        ),
                        Item(
                            quantity = 1,
                            name = "Guava",
                            description = "Product Three Description",
                            price = 3.0,
                            tax = 1.0,
                        ),
                    ),
                )
            }
        }
    }

    fun onConsumerKeyChanged(value: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(consumerKey = value) }
        }
    }

    fun onConsumerSecretChanged(value: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(consumerSecret = value) }
        }
    }

    fun onMerchantUUIDChanged(value: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(merchantUUID = value) }
        }
    }

    fun onDiscountChanged(value: String) {
        value.toDoubleOrNull()?.let { v ->
            if (v >= 0.0) {
                viewModelScope.launch {
                    val createPayment = _uiState.value.createPayment
                    _uiState.update { state ->
                        state.copy(createPayment = createPayment.copy(discount = v))
                    }
                }
            }
        }
    }

    fun onShippingChanged(value: String) {
        value.toDoubleOrNull()?.let { v ->
            if (v >= 0.0) {
                viewModelScope.launch {
                    val createPayment = _uiState.value.createPayment
                    _uiState.update { state ->
                        state.copy(createPayment = createPayment.copy(shipping = v))
                    }
                }
            }
        }
    }

    fun onTipChanged(value: String) {
        value.toDoubleOrNull()?.let { v ->
            if (v >= 0.0) {
                viewModelScope.launch {
                    val createPayment = _uiState.value.createPayment
                    _uiState.update { state ->
                        state.copy(createPayment = createPayment.copy(tip = v))
                    }
                }
            }
        }
    }

    fun onBuyerIdentityCodeChanged(value: String) {
        viewModelScope.launch {
            val createPayment = _uiState.value.createPayment
            _uiState.update { state ->
                state.copy(createPayment = createPayment.copy(buyerIdentityCode = value))
            }
        }
    }

    fun onCancelUrlChanged(value: String) {
        if (Pattern.matches(Patterns.WEB_URL.pattern(), value)) {
            viewModelScope.launch {
                val createPayment = _uiState.value.createPayment
                _uiState.update { state ->
                    state.copy(createPayment = createPayment.copy(cancelUrl = value))
                }
            }
        }
    }

    fun onReturnUrlChanged(value: String) {
        if (Pattern.matches(Patterns.WEB_URL.pattern(), value)) {
            viewModelScope.launch {
                val createPayment = _uiState.value.createPayment
                _uiState.update { state ->
                    state.copy(createPayment = createPayment.copy(returnUrl = value))
                }
            }
        }
    }

    fun onCurrencyChanged(value: String) {
        viewModelScope.launch {
            val createPayment = _uiState.value.createPayment
            _uiState.update { state ->
                state.copy(createPayment = createPayment.copy(currency = value))
            }
        }
    }

    fun onDescriptionChanged(value: String) {
        viewModelScope.launch {
            val createPayment = _uiState.value.createPayment
            _uiState.update { state ->
                state.copy(createPayment = createPayment.copy(description = value))
            }
        }
    }

    fun onInvoiceNumberChanged(value: String) {
        viewModelScope.launch {
            val createPayment = _uiState.value.createPayment
            _uiState.update { state ->
                state.copy(createPayment = createPayment.copy(invoiceNumber = value))
            }
        }
    }

    fun onMerchantOpIdChanged(value: String) {
        if (value.length <= 12) {
            value.toLongOrNull()?.let {
                viewModelScope.launch {
                    val createPayment = _uiState.value.createPayment
                    _uiState.update { state ->
                        state.copy(createPayment = createPayment.copy(merchantOpId = value))
                    }
                }
            }
        }
    }

    fun onTerminalIdChanged(value: String) {
        viewModelScope.launch {
            val createPayment = _uiState.value.createPayment
            _uiState.update { state ->
                state.copy(createPayment = createPayment.copy(terminalId = value))
            }
        }
    }

    fun onDialogClose() {
        viewModelScope.launch {
            _uiState.update { it.copy(textMessage = null, error = null) }
        }
    }

    fun onAuthButtonClick() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, textMessage = null, error = null) }
            enzona.initialize(
                apiUrl = Enzona.ApiUrl.OFFICIAL,
                merchantConsumerKey = _uiState.value.consumerKey,
                merchantConsumerSecret = _uiState.value.consumerSecret,
                merchantUUID = _uiState.value.merchantUUID,
            )
            when (val res = enzona.authenticate()) {
                is ResultValue.Success -> {
                    Log.d("dev/tag", "onAuthButtonClick: Success = $res")
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            textMessage = "Authenticated Successfully!",
                        )
                    }
                }

                is ResultValue.Error -> {
                    Log.d("dev/tag", "onAuthButtonClick: Error = $res")
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = res.exception.toString(),
                        )
                    }
                }
            }
        }
    }

    fun onCreatePaymentClick() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, textMessage = null, error = null) }
            val createPayment: CreatePayment = _uiState.value.createPayment
            when (val res = enzona.createPayment(
                discount = createPayment.discount,
                shipping = createPayment.shipping,
                tip = createPayment.tip,
                buyerIdentityCode = createPayment.buyerIdentityCode,
                cancelUrl = createPayment.cancelUrl,
                currency = createPayment.currency,
                description = createPayment.description,
                invoiceNumber = createPayment.invoiceNumber,
                merchantOpId = createPayment.merchantOpId.toLong(),
                returnUrl = createPayment.returnUrl,
                terminalId = createPayment.terminalId,
                items = _uiState.value.items,
            )) {
                is ResultValue.Success -> {
                    Log.d("dev/tag", "onCreatePaymentClick: Success = $res")
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            textMessage = "Payment created successfully!",
                        )
                    }
                }

                is ResultValue.Error -> {
                    Log.d("dev/tag", "onCreatePaymentClick: Error = $res")
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = res.exception.toString(),
                        )
                    }
                }
            }
        }
    }

}
