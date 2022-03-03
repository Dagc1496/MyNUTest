package com.example.mynutest.urlShortener.data.database.service

import com.example.mynutest.urlShortener.data.database.dao.UrlShortenedDao
import com.example.mynutest.urlShortener.data.remote.mapToDomain
import com.example.mynutest.urlShortener.domain.model.UrlShortened
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UrlShortenedListService @Inject constructor(private val urlShortenedDao : UrlShortenedDao) {

    fun fetchUrlShortenedList() : Flow<Result<List<UrlShortened>>>{
        return flow {
            val response = urlShortenedDao.getAllUrlShortened()
            val result = Result.success( response.map { it.mapToDomain() })
            emit(result)
        }.catch { exception ->
            /*
                generate logs because something is blowing up in database, so it will requiere
                some fix
             */
            emit(Result.failure(exception))
        }
    }

}