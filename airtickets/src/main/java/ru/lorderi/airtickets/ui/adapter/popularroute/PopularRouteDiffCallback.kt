package ru.lorderi.airtickets.ui.adapter.popularroute

import androidx.recyclerview.widget.DiffUtil
import ru.lorderi.airtickets.ui.data.TicketsOffer

class PopularRouteDiffCallback : DiffUtil.ItemCallback<TicketsOffer>() {
    override fun areItemsTheSame(oldItem: TicketsOffer, newItem: TicketsOffer): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TicketsOffer, newItem: TicketsOffer): Boolean =
        oldItem == newItem
}
