package com.phatnhse.hn.threads

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
        modules(commonModules, platformModule)
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
                onClick = {}
            ) {
                Text("Save")
            }

            Button(
                onClick = {}
            ) {
                Text(viewModel.ui.collectAsState().value.toString())
            }
        }
    }
}