package me.igorfedorov.androidapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.igorfedorov.androidapp.other.Constants.START_LOADING
import me.igorfedorov.androidapp.other.LoadingState
import me.igorfedorov.androidapp.ui.theme.AndroidAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val counter = remember { mutableStateOf(0) }

            val loadingState = remember { mutableStateOf(LoadingState.WAITING) }

            val enabled = remember { mutableStateOf(true) }
            viewModel.loadingState.observe(this) {
                enabled.value = it != LoadingState.LOADING
                loadingState.value = it
            }


            val buttonText = remember { mutableStateOf(START_LOADING) }
            viewModel.buttonText.observe(this) {
                buttonText.value = it
            }


            AndroidAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    SimpleExecutionOfHomework(
                        onLoadingButtonClick = {
                            viewModel.updateLoadingState()
                        },
                        onCounterButtonClick = {
                            counter.value++
                        },
                        enabled = enabled.value,
                        buttonText = buttonText.value,
                        count = counter.value,
                        loadingState = loadingState.value
                    )
                }
            }
        }
    }
}

@Composable
fun SimpleExecutionOfHomework(
    onLoadingButtonClick: () -> Unit,
    onCounterButtonClick: () -> Unit,
    enabled: Boolean,
    loadingState: LoadingState = LoadingState.WAITING,
    buttonText: String,
    count: Int = 0
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onLoadingButtonClick,
                enabled = enabled
            ) {
                Text(text = buttonText)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (loadingState == LoadingState.LOADING) {
                CircularProgressIndicator()
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onCounterButtonClick
            ) {
                Text(text = "Count ++")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Current count is: $count")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidAppTheme {
        SimpleExecutionOfHomework(
            onLoadingButtonClick = { /*TODO*/ },
            onCounterButtonClick = { /*TODO*/ },
            enabled = true,
            buttonText = START_LOADING
        )
    }
}