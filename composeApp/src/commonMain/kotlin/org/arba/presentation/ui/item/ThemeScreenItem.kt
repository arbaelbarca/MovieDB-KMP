package org.arba.presentation.ui.item

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.arba.utils.DarkColorPalette
import org.arba.utils.LightColorPalette

@Composable
fun ThemeScreenItem(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colorsTheme = if (isDarkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = colorsTheme,
        content = content
    )
}