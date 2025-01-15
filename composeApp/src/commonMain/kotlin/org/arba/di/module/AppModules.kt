package org.arba.di.module

import io.ktor.client.HttpClient
import org.arba.domain.repository.IMovieRepository
import org.arba.domain.repository.MovieRepository
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.arba.data.network.ClientNetwork
import org.arba.presentation.viewmodel.MovieViewModel

val provideViewModel = module {
    viewModel { MovieViewModel(get()) }
}

val provideRepostiry = module {
    singleOf(::MovieRepository).bind(IMovieRepository::class)
}

val provideHttpClient = module {
    singleOf(::ClientNetwork)
}

fun initializeKoin() {
    startKoin {
        modules(
            provideViewModel,
            provideRepostiry,
            provideHttpClient
        )
    }
}