package org.arba.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import movie_kmp.composeapp.generated.resources.Res
import movie_kmp.composeapp.generated.resources.compose_multiplatform
import org.arba.data.source.remote.MovieResponse
import org.arba.utils.BASE_URL_IMAGE
import org.arba.utils.Type
import org.arba.utils.getTypePlatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun MovieScreenItem(movieResponse: MovieResponse.Result) {

    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        model = BASE_URL_IMAGE + movieResponse.posterPath,
        contentDescription = "detailimage",
        contentScale = ContentScale.Fit,
        error = painterResource(Res.drawable.compose_multiplatform)
    )
}