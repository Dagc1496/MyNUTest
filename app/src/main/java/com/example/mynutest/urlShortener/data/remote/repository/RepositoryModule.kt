package com.example.mynutest.urlShortener.data.remote.repository

import com.example.mynutest.urlShortener.data.implementation.UrlShortenerRepository
import com.example.mynutest.urlShortener.domain.repository.IUrlShortenerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDomainRepository(
        urlShortenerRepository: UrlShortenerRepository
    ): IUrlShortenerRepository
}