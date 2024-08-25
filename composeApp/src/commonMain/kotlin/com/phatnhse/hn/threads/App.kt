package com.phatnhse.hn.threads

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phatnhse.hn.news.di.networkModule
import com.phatnhse.hn.threads.di.commonModules
import com.phatnhse.hn.threads.di.platformModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(commonModules, networkModule, platformModule)
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
    MaterialTheme {
        Column {
            Button(
                onClick = {
                    viewModel.request1()
                }
            ) {
                Text("Request news")
            }

            Text(viewModel.response.collectAsState().value)
        }

        if (viewModel.showProgressBar.collectAsState().value) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colors.secondary,
                backgroundColor = MaterialTheme.colors.surface,
            )
        }
    }
}