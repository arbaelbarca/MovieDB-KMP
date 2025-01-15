package org.arba.presentation.ui.dialog

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.arba.presentation.ui.item.ThemeScreenItem

@Composable
fun ThemeScreenDialog(
    isDialogOpen: Boolean,
    onDismissRequest: () -> Unit,
    onThemeSelected: (Boolean) -> Unit // `true` untuk Dark Theme, `false` untuk Light Theme
) {
    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            confirmButton = {
                TextButton(onClick = {
                    onDismissRequest()
                }) {
                    Text("OK")
                }
            },
            title = {
                Text("Choose Theme")
            },
            text = {
                ThemeRadioButtonGroup(onThemeSelected)
            }
        )
    }
}

@Composable
fun ThemeRadioButtonGroup(
    onThemeSelected: (Boolean) -> Unit
) {
    val options = listOf("Light", "Dark")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf("Light") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top
    ) {
        options.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (option == selectedOption),
                        onClick = {
                            onOptionSelected(option)
                            onThemeSelected(option == "Dark")
                        }
                    )
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = {
                        onOptionSelected(option)
                        onThemeSelected(option == "Dark")
                    }
                )
                Text(
                    text = option,
                    modifier = Modifier.padding(start = 8.dp),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}