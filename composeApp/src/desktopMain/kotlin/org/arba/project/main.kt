package org.arba.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.arba.di.module.initializeKoin

fun main() = application {
    initializeKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "Movie-KMP",
    ) {
        App()
    }
}