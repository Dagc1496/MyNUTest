package com.example.mynutest.urlShortener.domain.repository

import com.example.mynutest.urlShortener.domain.model.Url
import com.example.mynutest.urlShortener.domain.model.UrlShortened
import kotlinx.coroutines.flow.Flow

interface IUrlShortenerRepository {

    fun fetchShortenedUrlFromApi(url: Url): Flow<Result<UrlShortened>>

    fun fetchShortenedUrlsFromDatabase(): Flow<Result<List<UrlShortened>>>
}
