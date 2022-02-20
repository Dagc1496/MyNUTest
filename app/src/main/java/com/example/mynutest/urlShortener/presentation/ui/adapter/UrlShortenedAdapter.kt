package com.example.mynutest.urlShortener.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynutest.databinding.UrlShortenedItemBinding
import com.example.mynutest.urlShortener.domain.model.UrlShortened
import com.example.mynutest.urlShortener.presentation.ui.viewHolder.UrlShortenedViewHolder

class UrlShortenedAdapter(
    private val urlShortenedList: List<UrlShortened>
) : RecyclerView.Adapter<UrlShortenedViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UrlShortenedViewHolder {
        return UrlShortenedViewHolder(UrlShortenedItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false))
    }

    override fun onBindViewHolder(holder: UrlShortenedViewHolder, position: Int) {
        val urlShortened = urlShortenedList[position]

        holder.onBind(urlShortened = urlShortened)
    }

    override fun getItemCount(): Int = urlShortenedList.size

}