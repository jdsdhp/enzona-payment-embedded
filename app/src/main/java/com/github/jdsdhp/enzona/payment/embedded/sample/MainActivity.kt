package com.github.jdsdhp.enzona.payment.embedded.sample

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
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

    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.app_name),
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
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    }

    uiState.error?.let {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    }

}