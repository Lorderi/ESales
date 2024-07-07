package ru.lorderi.effectivesales.ui.adapter.searchoffers

import androidx.recyclerview.widget.RecyclerView
import ru.lorderi.effectivesales.databinding.CityCardBinding
import ru.lorderi.effectivesales.ui.Constants.searchOffersImages
import ru.lorderi.effectivesales.ui.data.SearchOffer

class SearchOffersViewHolder(
    private val binding: CityCardBinding,
    private val listener: (city: String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(searchOffer: SearchOffer) {
        binding.cityName.text = searchOffer.town
        binding.cityAvatar.setImageResource(searchOffersImages[searchOffer.id])
        binding.root.setOnClickListener {
            listener(searchOffer.town)
        }
    }
}