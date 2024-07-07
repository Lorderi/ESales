package ru.lorderi.airtickets.ui.adapter.searchoffers

import androidx.recyclerview.widget.DiffUtil
import ru.lorderi.airtickets.ui.data.SearchOffer

class SearchOffersDiffCallback : DiffUtil.ItemCallback<SearchOffer>() {
    override fun areItemsTheSame(oldItem: SearchOffer, newItem: SearchOffer): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SearchOffer, newItem: SearchOffer): Boolean =
        oldItem == newItem
}
