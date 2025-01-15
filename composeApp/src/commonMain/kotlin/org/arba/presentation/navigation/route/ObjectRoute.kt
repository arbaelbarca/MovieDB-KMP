package org.arba.presentation.navigation.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ObjectRoute(
    val route: String,
    var title: String,
    val defaultIcon: ImageVector
) {
    data object HomeScreen : ObjectRoute(
        route = "HOME",
        title = "Home",
        defaultIcon = Icons.Filled.Home,
    )
    data object SettingScreen : ObjectRoute(
        route = "Setting",
        title = "Setting",
        defaultIcon = Icons.Filled.Home,
    )
}