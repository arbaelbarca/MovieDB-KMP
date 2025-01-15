package org.arba.presentation.ui.item

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import movie_kmp.composeapp.generated.resources.Res
import movie_kmp.composeapp.generated.resources.compose_multiplatform
import org.arba.data.source.remote.MovieResponse
import org.arba.utils.BASE_URL_IMAGE
import org.arba.utils.Type
import org.arba.utils.getTypePlatform
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

@Composable
fun MovieScreenItem(movieResponse: MovieResponse.Result) {

    Card(
        modifier = Modifier
            .padding(7.dp),
        elevation = CardDefaults.cardElevation(7.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = BASE_URL_IMAGE + movieResponse.posterPath,
            contentDescription = "detailimage",
            contentScale = ContentScale.Fit,
            error = painterResource(Res.drawable.compose_multiplatform)
        )
    }

}

@Composable
fun MovieDiscoveryItem(
    imageUrls: List<String>,
    paddingValues: PaddingValues
) {
    val cards = remember { imageUrls.toMutableList() }
    var dragOffsetX by remember { mutableStateOf(0f) }
    val context = LocalPlatformContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        cards.forEachIndexed { index, imageUrl ->
            val offsetAnim = remember { Animatable(0f) }

            if (index == cards.size - 1) { // Topmost card
                Card(
                    modifier = Modifier
                        .offset { IntOffset(offsetAnim.value.roundToInt(), 0) }
                        .fillMaxWidth(0.8f)
                        .aspectRatio(0.7f)
                        .clip(RoundedCornerShape(16.dp))
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDrag = { change, dragAmount ->
                                    change.consume()
                                    dragOffsetX += dragAmount.x
                                    println("respon Drafofset $dragOffsetX")
                                },
                                onDragEnd = {
                                    if (dragOffsetX > 300 || dragOffsetX < -300) {
                                        cards.removeAt(index)
                                    }
                                    dragOffsetX = 0f
                                }
                            )
                        },
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Card Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            } else {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .aspectRatio(0.7f)
                        .clip(RoundedCornerShape(16.dp))
                        .offset(y = (20 * index).dp)
                        .scale(1f - (0.05f * index)),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Stacked Card Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}