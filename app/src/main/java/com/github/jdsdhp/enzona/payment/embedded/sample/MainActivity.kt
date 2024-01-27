package com.github.jdsdhp.enzona.payment.embedded.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.jdsdhp.enzona.payment.embedded.sample.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.authenticate),
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(R.string.consumer_key)) },
            value = uiState.consumerKey,
            onValueChange = { viewModel.onConsumerKeyChanged(it) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(R.string.consumer_secret)) },
            value = uiState.consumerSecret,
            onValueChange = { viewModel.onConsumerSecretChanged(it) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(R.string.merchant_uuid)) },
            value = uiState.merchantUUID,
            onValueChange = { viewModel.onMerchantUUIDChanged(it) },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.onAuthButtonClick() }) {
            Text(text = stringResource(R.string.authenticate))
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.create_payment),
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Currency") },
            value = uiState.createPayment.currency,
            onValueChange = { viewModel.onCurrencyChanged(it) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Cancel Url") },
            value = uiState.createPayment.cancelUrl,
            onValueChange = { viewModel.onCancelUrlChanged(it) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Return Url") },
            value = uiState.createPayment.returnUrl,
            onValueChange = { viewModel.onReturnUrlChanged(it) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Merchant Op Id (12 digits max)") },
            value = uiState.createPayment.merchantOpId,
            onValueChange = { viewModel.onMerchantOpIdChanged(it) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Description (Optional)") },
            value = uiState.createPayment.description,
            onValueChange = { viewModel.onDescriptionChanged(it) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Invoice Number (Optional)") },
            value = uiState.createPayment.invoiceNumber,
            onValueChange = { viewModel.onInvoiceNumberChanged(it) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Discount (Optional)") },
            value = uiState.createPayment.discount.toString(),
            onValueChange = { viewModel.onDiscountChanged(it) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Tip (Optional)") },
            value = uiState.createPayment.tip.toString(),
            onValueChange = { viewModel.onTipChanged(it) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Shipping (Optional)") },
            value = uiState.createPayment.shipping.toString(),
            onValueChange = { viewModel.onShippingChanged(it) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Buyer Identity Code (Optional)") },
            value = uiState.createPayment.buyerIdentityCode,
            onValueChange = { viewModel.onBuyerIdentityCodeChanged(it) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Terminal Id (Optional)") },
            value = uiState.createPayment.terminalId,
            onValueChange = { viewModel.onTerminalIdChanged(it) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            uiState.items.forEach {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = it.name + " (${it.quantity})")
                        Text(text = it.description)
                        Text(text = "$${it.price}")
                        Text(text = "Tax: ${it.tax}")
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.onCreatePaymentClick() }) {
            Text(text = stringResource(R.string.create_payment))
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider()

    }

    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }

    uiState.textMessage?.let {
        AlertDialog(
            text = { Text(text = it) },
            onDismissRequest = { viewModel.onDialogClose() },
            confirmButton = {
                Button(onClick = { viewModel.onDialogClose() }) {
                    Text(text = stringResource(R.string.accept))
                }
            },
        )
    }

    uiState.error?.let {
        AlertDialog(
            text = { Text(text = it) },
            onDismissRequest = { viewModel.onDialogClose() },
            confirmButton = {
                Button(onClick = { viewModel.onDialogClose() }) {
                    Text(text = stringResource(R.string.accept))
                }
            },
        )
    }

}