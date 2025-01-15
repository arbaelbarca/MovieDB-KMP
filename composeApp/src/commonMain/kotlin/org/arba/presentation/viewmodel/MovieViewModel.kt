package org.arba.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.arba.data.source.remote.MovieResponse
import org.arba.domain.repository.IMovieRepository
import org.arba.presentation.ui.state.UiStateApi

class MovieViewModel(val movieRepository: IMovieRepository) : ViewModel() {
    val mutableStateFlowMovie =
        MutableStateFlow<UiStateApi<MutableList<MovieResponse.Result>>>(UiStateApi.Idle)
    val stateFlowMovie: StateFlow<UiStateApi<MutableList<MovieResponse.Result>>> =
        mutableStateFlowMovie

    val stateFlowMovieDiscovery: StateFlow<UiStateApi<MutableList<MovieResponse.Result>>> =
        mutableStateFlowMovie

    fun callApiDataMovie() {
        viewModelScope.launch {
            mutableStateFlowMovie.emit(UiStateApi.Loading)
            runCatching {
                movieRepository.callApiMovie()
            }.onSuccess {
                val dataListResponse = it.body<MovieResponse>()
                mutableStateFlowMovie.emit(UiStateApi.Success(dataListResponse.results as MutableList<MovieResponse.Result>))
            }.onFailure {
                mutableStateFlowMovie.emit(UiStateApi.Error(it.message.toString()))
            }
        }
    }

    fun callApiDataMovieDiscovery() {
        viewModelScope.launch {
            mutableStateFlowMovie.emit(UiStateApi.Loading)
            runCatching {
                movieRepository.callApiMovieDiscovery()
            }.onSuccess {
                val dataListResponse = it.body<MovieResponse>()
                mutableStateFlowMovie.emit(UiStateApi.Success(dataListResponse.results as MutableList<MovieResponse.Result>))
            }.onFailure {
                mutableStateFlowMovie.emit(UiStateApi.Error(it.message.toString()))
            }
        }
    }

}