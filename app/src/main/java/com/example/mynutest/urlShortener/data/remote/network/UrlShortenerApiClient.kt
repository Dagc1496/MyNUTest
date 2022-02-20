package com.example.mynutest.urlShortener.data.remote.network

import com.example.mynutest.urlShortener.data.remote.dto.UrlRequest
import com.example.mynutest.urlShortener.data.remote.dto.UrlShortenedResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UrlShortenerApiClient  {

    @POST("/api/alias")
    suspend fun fetchUrlShorted(@Body url : UrlRequest): UrlShortenedResponse

}