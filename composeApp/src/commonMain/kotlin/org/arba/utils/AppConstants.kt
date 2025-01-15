package org.arba.utils

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

enum class Type {
    Mobile,
    Dekstop,
    Web
}

val DB_NAME = "todo.db"
val BASE_URL = "https://api.themoviedb.org/3/movie/"
val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500/"
val TOKEN_BEARER =
    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkY2Y1NzYzNjkxYTJhOTYwZDJkMjQyMDk3Y2RiMWY2YyIsIm5iZiI6MTUwMTkxMDAzNy40NzcsInN1YiI6IjU5ODU1NDEzOTI1MTQxNDM4NzAxOGMyZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.udJowX1b8e7BFzYKWXkVrS5GIZ4VZPxkyTUWiHSotsM"

val LightColorPalette = lightColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC5),
    background = Color.White,
)

val DarkColorPalette = darkColorScheme(
    primary = Color(0xFFBB86FC),
    secondary = Color(0xFF03DAC5),
    background = Color.Black,
)
