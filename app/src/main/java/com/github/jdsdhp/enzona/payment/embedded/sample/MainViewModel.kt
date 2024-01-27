package com.github.jdsdhp.enzona.payment.embedded.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.jdsdhp.enzona.payment.embedded.Enzona
import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val enzona: Enzona) : ViewModel() {

    data class UiState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val textMessage: String? = null,
        val consumerKey: String = "81go6n9Vz1gcSL2nK5mfZxJzDRwa",
        val consumerSecret: String = "MLZucMGr3z9k5kqp49jvwJCrWU4a",
        val merchantUUID: String = "2a5d8dfd49794387b408d2168a461da5",
    )

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

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
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            textMessage = "Authenticated Successfully!",
                        )
                    }
                }

                is ResultValue.Error -> {
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
