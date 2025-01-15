package org.arba.domain.repository

import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import org.arba.data.network.ClientNetwork
import org.arba.data.source.remote.MovieResponse

class MovieRepository(val clientNetwork: ClientNetwork) : IMovieRepository {
    override suspend fun callApiMovie(): HttpResponse {
        return clientNetwork.httpClient.get {
            url("popular?language=en-US")
        }
    }

    override suspend fun callApiMovieDiscovery(): HttpResponse {
        return clientNetwork.httpClient.get {
            url("popular?language=en-US")
        }
    }
}