package com.example.mynutest.urlShortener.presentation.ui.viewHolder

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynutest.databinding.UrlShortenedItemBinding
import com.example.mynutest.urlShortener.domain.model.UrlShortened

class UrlShortenedViewHolder(binding: UrlShortenedItemBinding) : RecyclerView.ViewHolder(binding.root) {

    val textViewOriginalUrl : TextView = binding.textViewOriginalUrl
    val textViewShortenedUrl : TextView = binding.textViewShortenedUrl

    fun onBind(urlShortened: UrlShortened){
        textViewOriginalUrl.text = urlShortened.links.self
        textViewShortenedUrl.text = urlShortened.links.short
    }
}