package com.example.mynutest.urlShortener.data.remote.network

import com.example.mynutest.urlShortener.data.remote.mapToDomain
import com.example.mynutest.urlShortener.data.remote.mapToDto
import com.example.mynutest.urlShortener.domain.model.Url
import com.example.mynutest.urlShortener.domain.model.UrlShortened
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UrlShortenerService @Inject constructor(private val apiClient: UrlShortenerApiClient) {

    fun fetchUrlShorted(url: Url) : Flow<Result<UrlShortened>> {
        return flow{
            val response = apiClient.fetchUrlShorted(url.mapToDto())
            val result = Result.success(response.mapToDomain())
            emit(result)
        }.catch { exception ->
            if(exception is IllegalArgumentException){
                /*
                    normally generate logs since the server is not delivering all the necessary information
                    think about it like an anti-corruption layer

                    Just for sonar issues return IllegalArgumentException() instead of exception, normally the issue of the
                    same code block should not appear with the implementation of logs or analytics.
                 */
                emit(Result.failure(java.lang.IllegalArgumentException()))
            }else{
                emit(Result.failure(exception))
            }
        }
    }
}