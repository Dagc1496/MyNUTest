package com.example.mynutest.urlShortener.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UrlShortenedResponse(
    @SerializedName("alias") val alias: String?,
    @SerializedName("_links") val links: LinksResponse?,
)