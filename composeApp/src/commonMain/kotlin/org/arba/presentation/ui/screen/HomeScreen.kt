package org.arba.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import org.arba.data.source.remote.MovieResponse
import org.arba.presentation.ui.item.MovieScreenItem
import org.arba.utils.Type
import org.arba.utils.getTypePlatform
import org.arba.presentation.viewmodel.MovieViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen() {
    val movieViewModel = koinViewModel<MovieViewModel>()
    val stateMovieViewModel = movieViewModel.stateFlowMovie.collectAsState()

    movieViewModel.callApiDataMovie()

//    val context = LocalPlatformContext.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        stateMovieViewModel.value.DisplayResult(
            onIdle = {

            },
            onLoading = {

            },
            onSuccess = {
                val dataList = it.toMutableList()
                if (dataList.isNotEmpty()) {
//                    Text("Success dapat data ${dataList[0].title}")
                    ShowScreenMovie(it)
                } else Text("Empty Data")
            },
            onError = {
                Text(it)
            },
        )

    }
}

@Composable
fun ShowScreenMovie(listMovie: List<MovieResponse.Result>) {
    val isDekstop = remember {
        getTypePlatform() == Type.Dekstop
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(if (isDekstop) 3 else 2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp),
        userScrollEnabled = false
    ) {
        items(listMovie) { itemData ->
            MovieScreenItem(itemData)
        }
    }
}