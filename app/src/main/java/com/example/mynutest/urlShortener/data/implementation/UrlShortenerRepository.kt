package com.example.mynutest.urlShortener.data.implementation

import com.example.mynutest.urlShortener.data.remote.network.UrlShortenerService
import com.example.mynutest.urlShortener.domain.model.Url
import com.example.mynutest.urlShortener.domain.model.UrlShortened
import com.example.mynutest.urlShortener.domain.repository.IUrlShortenerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UrlShortenerRepository @Inject constructor(
    private val urlShortenerService: UrlShortenerService
): IUrlShortenerRepository {

    override fun fetchShortenedUrl(url: Url): Flow<Result<UrlShortened>> {
        return urlShortenerService.fetchUrlShorted(url)
    }
}