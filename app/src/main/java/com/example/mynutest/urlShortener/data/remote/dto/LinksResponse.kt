package com.example.mynutest.urlShortener.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LinksResponse(
    @SerializedName("self") val self: String?,
    @SerializedName("short") val short: String?
)