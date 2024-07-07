package ru.lorderi.airtickets.ui.adapter.airlinetickets

import androidx.recyclerview.widget.DiffUtil
import ru.lorderi.airtickets.ui.data.Offer

class AirlineTicketsDiffCallback : DiffUtil.ItemCallback<Offer>() {
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean = oldItem == newItem
}
