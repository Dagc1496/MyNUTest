package com.example.mynutest.urlShortener.data.implementation

import com.example.mynutest.urlShortener.data.database.service.UrlShortenedListService
import com.example.mynutest.urlShortener.data.remote.service.UrlShortenerService
import com.example.mynutest.urlShortener.domain.model.Url
import com.example.mynutest.urlShortener.domain.model.UrlShortened
import com.example.mynutest.urlShortener.domain.repository.IUrlShortenerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UrlShortenerRepository @Inject constructor(
    private val urlShortenerService: UrlShortenerService,
    private val urlShortenedListService: UrlShortenedListService
): IUrlShortenerRepository {

    override fun fetchShortenedUrlFromApi(url: Url): Flow<Result<UrlShortened>> {
        return urlShortenerService.fetchUrlShorted(url)
    }

    override fun fetchShortenedUrlsFromDatabase(): Flow<Result<List<UrlShortened>>> {
        return urlShortenedListService.fetchUrlShortenedList()
    }
}