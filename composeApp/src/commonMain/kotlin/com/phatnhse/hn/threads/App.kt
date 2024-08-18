package com.phatnhse.hn.threads

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App(
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