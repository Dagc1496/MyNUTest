package com.example.mynutest.urlShortener.domain.useCase

import com.example.mynutest.urlShortener.domain.model.Url
import com.example.mynutest.urlShortener.domain.model.UrlShortened
import com.example.mynutest.urlShortener.domain.repository.IUrlShortenerRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@kotlinx.coroutines.ExperimentalCoroutinesApi
class UrlShortenerUseCaseTest {

    private val repository = mock<IUrlShortenerRepository>()
    private val urlShortenerUseCase = UrlShortenerUseCase(repository)

    private val urlSuccess = Url("Testing")
    private val urlError = Url("")

    private val urlShortened = mock<UrlShortened>()
    private val expectedSuccess = Result.success(urlShortened)
    private val expectedError = Result.failure<UrlShortened>(IllegalArgumentException())

    @Test
    fun callingRepositoryOneTime() = runBlockingTest{

        //Act
        urlShortenerUseCase.invoke(urlSuccess)

        //Assert
        verify(repository, times(1)).fetchShortenedUrlFromApi(urlSuccess)
    }

    @Test
    fun fetchingShortedUrlSuccess() = runBlockingTest {

        //Arrange
        whenever(repository.fetchShortenedUrlFromApi(urlSuccess)).thenReturn(
            flow {
                emit(expectedSuccess)
            }
        )

        //Act
        val result = urlShortenerUseCase.invoke(urlSuccess).first()

        //Assert
        assertEquals(expectedSuccess, result)
    }

    @Test
    fun fetchingShortedUrlError() = runBlockingTest {

        //Arrange
        whenever(repository.fetchShortenedUrlFromApi(urlError)).thenReturn(
            flow {
                emit(expectedError)
            }
        )

        //Act
        val result = urlShortenerUseCase.invoke(urlError).first()

        //Assert
        assertEquals(expectedError, result)
    }
}