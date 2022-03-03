package com.example.mynutest.urlShortener.data.remote

import com.example.mynutest.common.exception.IllegalEntryStateArgument
import com.example.mynutest.urlShortener.data.database.entities.LinksEntity
import com.example.mynutest.urlShortener.data.database.entities.UrlShortenedEntity
import com.example.mynutest.urlShortener.data.remote.dto.LinksResponse
import com.example.mynutest.urlShortener.data.remote.dto.UrlRequest
import com.example.mynutest.urlShortener.data.remote.dto.UrlShortenedResponse
import com.example.mynutest.urlShortener.domain.model.Links
import com.example.mynutest.urlShortener.domain.model.Url
import com.example.mynutest.urlShortener.domain.model.UrlShortened
import java.lang.IllegalArgumentException

fun LinksResponse.mapToDomain(): Links =
    Links(
        self = self ?: throw  IllegalArgumentException(),
        short = short ?: throw IllegalArgumentException()
    )

fun LinksResponse.mapToEntity(): LinksEntity =
    LinksEntity(
        self = self ?: throw  IllegalArgumentException(),
        short = short ?: throw IllegalArgumentException()
    )

fun UrlShortenedResponse.mapToDomain(): UrlShortened =
    UrlShortened(
        alias = alias ?: throw IllegalArgumentException(),
        links = links?.mapToDomain() ?: throw IllegalArgumentException()
    )

fun UrlShortenedResponse.mapToEntity(): UrlShortenedEntity =
    UrlShortenedEntity(
        alias = alias ?: throw IllegalArgumentException(),
        links = links?.mapToEntity() ?: throw IllegalArgumentException()
    )

fun Url.mapToDto(): UrlRequest =
    UrlRequest(
        url = url.ifBlank { throw IllegalEntryStateArgument() }
    )

fun UrlShortenedEntity.mapToDomain(): UrlShortened =
    UrlShortened(
        alias = alias,
        links = links.mapToDomain()
    )

fun LinksEntity.mapToDomain(): Links =
    Links(
        short = short,
        self = self
    )
