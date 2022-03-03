package com.example.mynutest.urlShortener.data.database.entities

import androidx.room.ColumnInfo

data class LinksEntity(
    @ColumnInfo(name = "self")
    var self: String = "",

    @ColumnInfo(name = "short")
    var short: String = "m"
)
