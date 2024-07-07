package ru.lorderi.effectivesales.ui.adapter.searchoffers


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.lorderi.effectivesales.databinding.CityCardBinding
import ru.lorderi.effectivesales.ui.data.SearchOffer

class SearchOffersAdapter(private val listener: (city: String) -> Unit) :
    ListAdapter<SearchOffer, SearchOffersViewHolder>(SearchOffersDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchOffersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CityCardBinding.inflate(layoutInflater, parent, false)

        val viewHolder = SearchOffersViewHolder(binding, listener)

        return viewHolder
    }

    override fun onBindViewHolder(holder: SearchOffersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}