package com.example.mynutest.urlShortener.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UrlRequest(
    @SerializedName("url") val url: String?
)
