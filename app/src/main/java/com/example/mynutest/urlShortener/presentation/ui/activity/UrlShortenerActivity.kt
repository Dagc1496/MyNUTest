package com.example.mynutest.urlShortener.presentation.ui.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynutest.R
import com.example.mynutest.common.exception.IllegalEntryStateArgument
import com.example.mynutest.databinding.ActivityUrlShortenerBinding
import com.example.mynutest.urlShortener.domain.model.Url
import com.example.mynutest.urlShortener.domain.model.UrlShortened
import com.example.mynutest.urlShortener.presentation.ui.adapter.UrlShortenedAdapter
import com.example.mynutest.urlShortener.presentation.viewModel.UrlShortenedListViewModel
import com.example.mynutest.urlShortener.presentation.viewModel.UrlShortenerViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class UrlShortenerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUrlShortenerBinding
    private var urlShortenedList = listOf<UrlShortened>()
    private val urlShortenerViewModel: UrlShortenerViewModel by viewModels()
    private val urlShortenerListViewModel: UrlShortenedListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUrlShortenerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            urlShortenerViewModel.loader.collect {
                setLoadingState(it)
            }
        }

        fetchShortenedList()

        lifecycleScope.launchWhenStarted {
            urlShortenerListViewModel.loader.collect {
                setLoadingState(it)
            }
        }

        binding.buttonShorUrl.setOnClickListener {
            val url:String = binding.editTextUri.text.toString()
            hideKeyboard(it)

            fetchShortedUrl(url)
        }
    }

    private fun fetchShortenedList(){
        urlShortenerListViewModel.fetchShortenedUrlList().observe(this){ it ->
            it.fold(
                onSuccess = {
                    urlShortenedList = it
                    setupList()
                },
                onFailure = {
                    showSnackbar(getErrorMessage(it).ifBlank { resources.getString(R.string.try_again) })
                }
            )
        }
    }

    private fun fetchShortedUrl(urlText: String){
        val url = Url(urlText)
        urlShortenerViewModel.fetchShortenedUrl(url).observe(this){ it ->
            it.fold(
                onSuccess = {
                    binding.editTextUri.text.clear()
                    fetchShortenedList()
                },
                onFailure = {
                    showSnackbar(getErrorMessage(it).ifBlank { resources.getString(R.string.try_again) })
                }
            )
        }
    }

    private fun setupList(){
        with(binding.recyclerViewRecentUrls){
            layoutManager = LinearLayoutManager(context)
            adapter = UrlShortenedAdapter(urlShortenedList)
        }
    }

    private fun setLoadingState(loading :Boolean){
        binding.progressBarLoader.isVisible = loading
        binding.linearLayoutUrl.isVisible = !loading
        binding.textViewRecentShortenedUrl.isVisible = !loading
        binding.recyclerViewRecentUrls.isVisible = !loading
    }

    private fun getErrorMessage(exception: Throwable) : String {
        if(exception is IllegalEntryStateArgument){
            return resources.getString(R.string.please_validate_data)
        }
        if(exception is IllegalArgumentException){
            return resources.getString(R.string.unexpected_error)
        }else{
            return resources.getString(R.string.sorry_we_have_problems)
        }
    }

    //Normally in a BaseActivity Class
    private fun showSnackbar(message:String){
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun Activity.hideKeyboard(view: View){
        val imm: InputMethodManager = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}