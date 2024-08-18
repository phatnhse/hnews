package com.phatnhse.hn.threads

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "HN Threads",
    ) {
        App()
    }
}