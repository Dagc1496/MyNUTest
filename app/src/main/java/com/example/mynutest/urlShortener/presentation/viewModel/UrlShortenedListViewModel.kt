package com.example.mynutest.urlShortener.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.mynutest.urlShortener.domain.model.UrlShortened
import com.example.mynutest.urlShortener.domain.useCase.UrlShortenedListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UrlShortenedListViewModel @Inject constructor(
    private val urlShortenedListUseCase: UrlShortenedListUseCase
): ViewModel() {

    private val _loader = MutableStateFlow(false)
    val loader: StateFlow<Boolean> get() = _loader

    private val urlShortedList = MutableLiveData<Result<List<UrlShortened>>>()

    fun fetchShortenedUrlList() = liveData {
        _loader.value = true
        emitSource(urlShortenedListUseCase.invoke().onEach {
            urlShortedList.postValue(it)
            _loader.value = false
        }.asLiveData())
    }
}