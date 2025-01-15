package org.arba.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.arba.presentation.ui.dialog.ThemeScreenDialog
import org.arba.presentation.ui.item.ThemeScreenItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController) {
    val listSetting = listOf("Themes")

    var selectedItem by remember {
        mutableStateOf("")
    }

    var isDialogOpen by remember { mutableStateOf(false) }
//    var isDarkTheme by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Settings")
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(paddingValues)
                        .padding(10.dp)
                ) {
                    items(listSetting) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .clickable {
                                    selectedItem = item
                                    isDialogOpen = true
                                },
                            elevation = CardDefaults.cardElevation(7.dp)
                        ) {
                            Text(item)
                        }
                    }
                }
            }
            println("respon SelectedITem $selectedItem")


            val systemInDarkTheme = isSystemInDarkTheme()
            // Mutable state to track theme mode
            var isDarkTheme by remember { mutableStateOf(systemInDarkTheme) }

            if (selectedItem.equals("Themes")) {
                ThemeScreenDialog(
                    isDialogOpen = isDialogOpen,
                    onDismissRequest = { isDialogOpen = false },
                    onThemeSelected = { selectedDarkTheme ->
                        isDarkTheme = selectedDarkTheme
                    }
                )

                if (isDarkTheme) {
                    ThemeScreenItem(
                        isDarkTheme,
                        content = {

                        }
                    )
                }
            }
        }
    )
}