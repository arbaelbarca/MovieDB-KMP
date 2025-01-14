package org.arba.domain.repository

import io.ktor.client.statement.HttpResponse
import org.arba.data.source.remote.MovieResponse

interface IMovieRepository {
   suspend fun callApiMovie(): HttpResponse
}