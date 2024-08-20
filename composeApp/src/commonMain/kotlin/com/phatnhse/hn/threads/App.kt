package com.phatnhse.hn.threads

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.testing.di.networkModules
import com.phatnhse.hn.threads.di.commonModules
import com.phatnhse.hn.threads.di.platformModule
import io.ktor.client.HttpClient
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(commonModules, networkModules, platformModule)
    }) {
        AppContent()
    }
}

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun AppContent(
    viewModel: AppViewModel = koinViewModel<AppViewModel>()
) {
    val httpClient: HttpClient = koinInject<HttpClient>()

    MaterialTheme {
        Column {
            Button(
                onClick = {
                    viewModel.request()
                }
            ) {
                val text = viewModel.response.collectAsState().value.ifEmpty {
                    httpClient.hashCode().toString()
                }
                Text(text)
            }

            Button(
                onClick = {}
            ) {
                Text(viewModel.ui.collectAsState().value.toString())
            }
        }
    }
}