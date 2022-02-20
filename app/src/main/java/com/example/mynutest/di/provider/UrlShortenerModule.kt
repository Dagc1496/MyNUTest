package com.example.mynutest.di.provider

import com.example.mynutest.BuildConfig
import com.example.mynutest.urlShortener.data.remote.network.UrlShortenerApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object UrlShortenerModule {

    @Provides
    fun provideUrlShortenerApiClient(retrofit: Retrofit): UrlShortenerApiClient {
        return retrofit.create(UrlShortenerApiClient::class.java)
    }

    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_HEROKU_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}