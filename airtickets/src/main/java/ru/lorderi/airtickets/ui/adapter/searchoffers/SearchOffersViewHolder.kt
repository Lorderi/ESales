package ru.lorderi.airtickets.ui.adapter.searchoffers

import androidx.recyclerview.widget.RecyclerView
import ru.lorderi.airtickets.databinding.CityCardBinding
import ru.lorderi.airtickets.ui.Constants.searchOffersImages
import ru.lorderi.airtickets.ui.data.SearchOffer

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