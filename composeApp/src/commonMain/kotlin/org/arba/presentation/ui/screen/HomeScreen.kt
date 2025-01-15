package org.arba.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.LocalPlatformContext
import org.arba.data.source.remote.MovieResponse
import org.arba.presentation.navigation.route.ObjectRoute
import org.arba.presentation.ui.item.MovieDiscoveryItem
import org.arba.presentation.ui.item.MovieScreenItem
import org.arba.utils.Type
import org.arba.utils.getTypePlatform
import org.arba.presentation.viewmodel.MovieViewModel
import org.arba.utils.BASE_URL_IMAGE
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val movieViewModel = koinViewModel<MovieViewModel>()

    movieViewModel.callApiDataMovieDiscovery()
    movieViewModel.callApiDataMovie()

//    val context = LocalPlatformContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Movie")
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(ObjectRoute.SettingScreen.route)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(paddingValues)
            ) {
                ShowMovieDiscovery(
                    movieViewModel,
                    paddingValues
                )

                ShowMoviePopular(
                    movieViewModel,
                    paddingValues
                )


            }
        }
    )
}

@Composable
fun ShowMoviePopular(
    movieViewModel: MovieViewModel,
    paddingValues: PaddingValues
) {
    val stateMovieViewModel = movieViewModel.stateFlowMovie.collectAsState()
    stateMovieViewModel.value.DisplayResult(
        onIdle = {

        },
        onLoading = {

        },
        onSuccess = {
            val dataList = it.toMutableList()
            if (dataList.isNotEmpty()) {
                ShowScreenMovie(it, paddingValues)
            } else Text("Empty Data")
        },
        onError = {
            Text(it)
        },
    )
}

@Composable
fun ShowMovieDiscovery(
    movieViewModel: MovieViewModel,
    paddingValues: PaddingValues
) {
    val stateMovieDiscoveryViewModel = movieViewModel.stateFlowMovieDiscovery.collectAsState()
    stateMovieDiscoveryViewModel.value.DisplayResult(
        onIdle = {

        },
        onLoading = {

        },
        onSuccess = {
            val dataListDiscover = it.toMutableList()
            if (dataListDiscover.isNotEmpty()) {
                dataListDiscover.forEach { dataItem ->
                    val listImage = listOf(BASE_URL_IMAGE + dataItem.posterPath)
                    println("respon List Image $listImage")
                    MovieDiscoveryItem(
                        listImage,
                        paddingValues = paddingValues
                    )
                }
            } else Text("Empty Data")
        },
        onError = {
            Text(it)
        },
    )
}


@Composable
fun ShowScreenMovie(
    listMovie: List<MovieResponse.Result>,
    paddingValues: PaddingValues
) {
    val isDekstop = remember {
        getTypePlatform() == Type.Dekstop
    }

//    LazyVerticalGrid(
//        modifier = Modifier
//            .padding(paddingValues),
//        columns = GridCells.Fixed(if (isDekstop) 3 else 2),
//        horizontalArrangement = Arrangement.spacedBy(10.dp),
//        contentPadding = PaddingValues(10.dp),
//        userScrollEnabled = true
//    ) {
//        items(listMovie) { itemData ->
//            MovieScreenItem(itemData)
//        }
//    }

    LazyRow(
        modifier = Modifier
            .padding(paddingValues)
            .padding(20.dp)
    ) {
        items(listMovie) { itemData ->
            MovieScreenItem(itemData)
        }
    }
}