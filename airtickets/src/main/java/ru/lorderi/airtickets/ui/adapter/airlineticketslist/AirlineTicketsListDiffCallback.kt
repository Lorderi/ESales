package ru.lorderi.airtickets.ui.adapter.airlineticketslist

import androidx.recyclerview.widget.DiffUtil
import ru.lorderi.airtickets.ui.data.Ticket

class AirlineTicketsListDiffCallback : DiffUtil.ItemCallback<Ticket>() {
    override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean =
        oldItem == newItem
}
