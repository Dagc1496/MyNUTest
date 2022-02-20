package com.example.mynutest.urlShortener.presentation.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.mynutest.urlShortener.domain.model.Url
import com.example.mynutest.urlShortener.domain.model.UrlShortened
import com.example.mynutest.urlShortener.domain.useCase.UrlShortenerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UrlShortenerViewModel @Inject constructor(
    private val urlShortenerUseCase: UrlShortenerUseCase
): ViewModel() {

    private val _loader = MutableStateFlow(false)
    val loader: StateFlow<Boolean> get() = _loader

    private val urlShorted = MutableLiveData<Result<UrlShortened>>()

    fun fetchShortenedUrl(url: Url) = liveData {
        _loader.value = true
        emitSource(urlShortenerUseCase.invoke(url = url).onEach {
            urlShorted.postValue(it)
            _loader.value = false
        }.asLiveData())
    }
}