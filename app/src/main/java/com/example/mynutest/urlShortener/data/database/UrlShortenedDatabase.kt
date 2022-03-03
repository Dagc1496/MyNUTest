package com.example.mynutest.urlShortener.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynutest.urlShortener.data.database.dao.UrlShortenedDao
import com.example.mynutest.urlShortener.data.database.entities.UrlShortenedEntity

@Database(entities = [UrlShortenedEntity::class], version = 1)
abstract class UrlShortenedDatabase: RoomDatabase() {

    abstract fun getUrlShortenedDao(): UrlShortenedDao

}