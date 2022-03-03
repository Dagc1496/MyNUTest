package com.example.mynutest.urlShortener.domain.useCase

import com.example.mynutest.urlShortener.domain.model.UrlShortened
import com.example.mynutest.urlShortener.domain.repository.IUrlShortenerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UrlShortenedListUseCase @Inject constructor(private val urlShortenerRepository: IUrlShortenerRepository) {

    operator fun invoke(): Flow<Result<List<UrlShortened>>> =
        urlShortenerRepository.fetchShortenedUrlsFromDatabase()
}