package com.example.mynutest.urlShortener.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mynutest.urlShortener.data.database.entities.UrlShortenedEntity

@Dao
interface UrlShortenedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(urlShortened: UrlShortenedEntity)

    @Query("SELECT * FROM urlShortened_table")
    suspend fun getAllUrlShortened() : List<UrlShortenedEntity>
}