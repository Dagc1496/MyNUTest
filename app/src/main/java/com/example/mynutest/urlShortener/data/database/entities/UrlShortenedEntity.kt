package com.example.mynutest.urlShortener.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "urlShortened_table")
data class UrlShortenedEntity (

    @ColumnInfo(name = "alias")
    val alias: String,

    @Embedded
    val links: LinksEntity
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
