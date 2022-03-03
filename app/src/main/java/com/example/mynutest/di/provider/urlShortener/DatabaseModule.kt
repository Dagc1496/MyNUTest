package com.example.mynutest.di.provider.urlShortener

import android.content.Context
import androidx.room.Room
import com.example.mynutest.urlShortener.data.database.UrlShortenedDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class DatabaseModule {

    private val URLSHORTENED_DATABASE_NAME = "urlShortened_database"

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, UrlShortenedDatabase::class.java, URLSHORTENED_DATABASE_NAME).build()

    @Provides
    fun provideUrlShortenedDao(database: UrlShortenedDatabase) = database.getUrlShortenedDao()

}