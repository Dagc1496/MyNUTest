package com.example.mynutest.urlShortener.data.remote.network

import com.example.mynutest.common.exception.IllegalEntryStateArgument
import com.example.mynutest.urlShortener.data.database.dao.UrlShortenedDao
import com.example.mynutest.urlShortener.data.remote.dto.LinksResponse
import com.example.mynutest.urlShortener.data.remote.dto.UrlRequest
import com.example.mynutest.urlShortener.data.remote.dto.UrlShortenedResponse
import com.example.mynutest.urlShortener.data.remote.service.UrlShortenerService
import com.example.mynutest.urlShortener.domain.model.Links
import com.example.mynutest.urlShortener.domain.model.Url
import com.example.mynutest.urlShortener.domain.model.UrlShortened
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

@kotlinx.coroutines.ExperimentalCoroutinesApi
class UrlShortenerServiceTest {

    private val apiclient = mock<UrlShortenerApiClient>()
    private val urlShortenerDao = mock<UrlShortenedDao>()

    private val service = UrlShortenerService(apiclient, urlShortenerDao)

    private val urlRequest = UrlRequest("Testing")
    private val url = Url("Testing")
    private val urlRequestError = UrlRequest("")
    private val urlError = Url("")
    private val expectedSuccess = UrlShortened("alias", Links("self", "short"))
    private val expectedError = Result.failure<UrlShortened>(IllegalEntryStateArgument())

    @Test
    fun fetchingShortedUrlSuccess() = runBlockingTest {

        //Arrange
        whenever(apiclient.fetchUrlShorted(urlRequest)).thenReturn(
            UrlShortenedResponse("alias", LinksResponse("self", "short"))
        )

        //Act
        val result = service.fetchUrlShorted(url).first().getOrNull()

        //Assert
        Assert.assertEquals(expectedSuccess, result)
    }

    @Test
    fun fetchingShortedUrlError() = runBlockingTest {

        //Arrange
        whenever(apiclient.fetchUrlShorted(urlRequestError)).thenThrow(
            IllegalStateException()
        )

        //Act
        val result = service.fetchUrlShorted(urlError).first()

        //Assert
        Assert.assertEquals(expectedError.exceptionOrNull()?.message, result.exceptionOrNull()?.message)
    }

}