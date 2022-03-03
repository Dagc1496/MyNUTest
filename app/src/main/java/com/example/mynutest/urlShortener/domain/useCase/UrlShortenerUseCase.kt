package com.example.mynutest.urlShortener.domain.useCase

import com.example.mynutest.urlShortener.domain.model.Url
import com.example.mynutest.urlShortener.domain.model.UrlShortened
import com.example.mynutest.urlShortener.domain.repository.IUrlShortenerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UrlShortenerUseCase @Inject constructor(private val urlShortenerRepository: IUrlShortenerRepository) {

     operator fun invoke(url: Url): Flow<Result<UrlShortened>> =
        urlShortenerRepository.fetchShortenedUrlFromApi(url)

}